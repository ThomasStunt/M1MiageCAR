package classes;

import java.rmi.RemoteException;
import java.util.UUID;

import interfaces.IClient;

public class Client implements IClient {

	String login = UUID.randomUUID().toString();
	
	public Client() {
		
	}
	
	public void receive(Message m) throws RemoteException {
		System.out.println(m);
	}

	public String getLogin() throws RemoteException {
		return login;
	}
	
}
