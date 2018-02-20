package classes;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import interfaces.IClient;
import interfaces.IServer;

public class Server extends UnicastRemoteObject implements IServer {

	private static final long serialVersionUID = 6753179322829639363L;
	
	protected Map<IClient, String> clientsRegistered;
	protected List<IClient> clientsConnected;
	
	public Server() throws RemoteException {
		super();
		clientsRegistered = new HashMap<IClient, String>();
		clientsConnected = new ArrayList<IClient>();
	}
	
	@Override
	public void register(IClient c, String pwd) throws RemoteException {
		
		if(!clientsRegistered.containsKey(c)) {
			clientsRegistered.put(c, pwd);
			this.connect(c, pwd);
		} else {
			System.out.println("[ERROR] User already exists.");
		}
		
	}

	@Override
	public boolean send(Message m) throws RemoteException {
		for(IClient c : clientsConnected)
			c.receive(m);
		
		return false;
	}

	@Override
	public void disconnect(IClient c) throws RemoteException {
		clientsConnected.remove(c);
	}

	@Override
	public void connect(IClient c, String pwd) throws RemoteException {
		if(clientsRegistered.containsKey(c) && pwd.contentEquals(clientsRegistered.get(c))) {
			clientsConnected.add(c);
			System.out.println("[INFO] Client connected.");
		} else {
			System.out.println("[ERROR] Couldn't connect.");
		}
	}

}
