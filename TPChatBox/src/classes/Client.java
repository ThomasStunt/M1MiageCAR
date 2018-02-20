package classes;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interfaces.IClient;
import interfaces.IMessage;

public class Client extends UnicastRemoteObject implements IClient {

	private static final long serialVersionUID = 5702976698815591119L;

	protected String login;
	protected String passwd;
	
	public Client(String login, String passwd) throws RemoteException {
		super();
		this.login = login;
		this.passwd = passwd;
	}
	
	public void receive(IMessage m) throws RemoteException {
		System.out.println(m);
	}

	public String getLogin() {
		return login;
	}
	
}
