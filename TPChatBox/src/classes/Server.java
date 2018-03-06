package classes;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import interfaces.IClient;
import interfaces.IMessage;
import interfaces.IServer;

public class Server extends UnicastRemoteObject implements IServer {

	private static final long serialVersionUID = 6753179322829639363L;

	protected Map<String, String> clientsRegistered;
	protected Map<IClient, String> clientsConnected;
	protected IClient servClient;

	public Server() throws RemoteException {
		super();
		clientsRegistered = new HashMap<String, String>();
		clientsConnected = new HashMap<IClient, String>();
		servClient = new Client("Server", "admin", null);
	}

	@Override
	public boolean register(IClient c, String pwd) throws RemoteException {
		if (!clientsRegistered.containsKey(c.getLogin())) {
			clientsRegistered.put(c.getLogin(), pwd);
			return this.connect(c, pwd);
		} else {
			return false;
		}

	}

	@Override
	public boolean send(IMessage m) throws RemoteException {
		if (m.getContent().charAt(0) == '@') {
			IClient clientMP = null;
			String words[] = m.getContent().substring(1, m.getContent().length()).split(" ");
			for (IClient c : clientsConnected.keySet()) {
				if (c.getLogin().toLowerCase().contentEquals(words[0].toLowerCase())) {
					clientMP = c;
				}
			}
			if (clientMP != null) {
				clientMP.receive(m, true);
				m.getSource().receive(m, false);
			}
			return true;
		} else {
			for (IClient c : clientsConnected.keySet())
				c.receive(m, false);
			return true;
		}
	}

	@Override
	public void disconnect(IClient c) throws RemoteException {
		clientsConnected.remove(c);
		IMessage iMsg = new Message(servClient, "[INFO] User " + c.getLogin() + " left the room.", false);
		this.send(iMsg);
	}

	@Override
	public boolean connect(IClient c, String pwd) throws RemoteException {
		boolean isConnected = false;
		if (clientsRegistered.containsKey(c.getLogin()) && pwd.contentEquals(clientsRegistered.get(c.getLogin()))) {
			if (clientsConnected.containsValue(c.getLogin())) {
				isConnected = true;
			} else {
				if (!isConnected) {
					clientsConnected.put(c, c.getLogin());
					IMessage iMsg = new Message(servClient, "[INFO] User " + c.getLogin() + " joined the room.", false);
					this.send(iMsg);
				}
			}
		} else {
			System.out.println("[ERROR] Couldn't connect.");
			isConnected = true;
		}
		return !isConnected;
	}

	public Map<IClient, String> getClientsConnected() {
		return clientsConnected;
	}

}
