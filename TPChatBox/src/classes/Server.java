package classes;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import interfaces.IServer;

public class Server extends UnicastRemoteObject implements IServer {

	private static final long serialVersionUID = 6753179322829639363L;
	
	protected Map<Client, String> clientsRegistered;
	protected List<Client> clientsConnected;
	
	public Server() throws RemoteException {
		super();
		clientsRegistered = new HashMap<Client, String>();
		clientsConnected = new ArrayList<Client>();
	}
	
	@Override
	public void register(Client c, String pwd) throws RemoteException {
		
		if(!clientsRegistered.containsKey(c)) {
			clientsRegistered.put(c, pwd);
			this.connect(c);
		} else {
			System.out.println("[ERROR] User already exists.");
		}
		
	}

	@Override
	public boolean send(Message m) throws RemoteException {
		for(Client c : clientsConnected)
			c.receive(m);
		
		return false;
	}

	@Override
	public void disconnect(Client c) throws RemoteException {
		clientsConnected.remove(c);
	}

	@Override
	public void connect(Client c) throws RemoteException {
		clientsConnected.add(c);
	}

}
