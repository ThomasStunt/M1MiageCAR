package classes;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import interfaces.IClient;
import interfaces.IMessage;
import interfaces.IServer;

public class Server extends UnicastRemoteObject implements IServer {

	private static final long serialVersionUID = 6753179322829639363L;
	
	protected Map<IClient, String> clientsRegistered;
	protected List<IClient> clientsConnected;
	protected IClient servClient;
	
	public Server() throws RemoteException {
		super();
		clientsRegistered = new HashMap<IClient, String>();
		clientsConnected = new ArrayList<IClient>();
		servClient = new Client("Server", "admin");
	}
	
	@Override
	public void register(IClient c, String pwd) throws RemoteException {
		if(!clientsRegistered.containsKey(c)) {
			clientsRegistered.put(c, pwd);
			this.connect(c, pwd);
			System.out.println("[INFO] Numbers of registered users : "+clientsRegistered.size());
		} else {
			System.out.println("[ERROR] User already exists.");
		}
		
	}

	@Override
	public boolean send(IMessage m) throws RemoteException {
		for(IClient c : clientsConnected)
			c.receive(m);
		
		return false;
	}

	@Override
	public void disconnect(IClient c) throws RemoteException {
		clientsConnected.remove(c);
		IMessage iMsg = new Message(servClient, "[INFO] User "+c.getLogin()+" left the room.");
		this.send(iMsg);
	}

	@Override
	public void connect(IClient c, String pwd) throws RemoteException {
		System.out.println(clientsRegistered.containsKey(c));
		if(clientsRegistered.containsKey(c) && pwd.contentEquals(clientsRegistered.get(c))) {
			clientsConnected.add(c);
			System.out.println("[INFO] Client connected.");
			IMessage iMsg = new Message(servClient, "[INFO] User "+c.getLogin()+" joined the room.");
			this.send(iMsg);
			System.out.println("[INFO] Numbers of connected users : "+clientsConnected.size());
		} else {
			System.out.println("[ERROR] Couldn't connect.");
		}
	}
	
	public List<IClient> getClientsConnected() {
		return clientsConnected;
	}

}
