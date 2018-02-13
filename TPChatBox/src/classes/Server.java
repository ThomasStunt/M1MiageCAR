package classes;

import java.rmi.RemoteException;

import interfaces.ServerInterface;

public class Server implements ServerInterface {

	Integer port;
	
	public Server(int port) {
		this.port = port;
	}
	
	@Override
	public void register(Client c) throws RemoteException {

	}

	@Override
	public boolean send(Message m) {
		return false;
	}

	@Override
	public void disconnect(Client c) {

	}

}
