package classes;

import java.rmi.RemoteException;

import interfaces.ServerInterface;

public class Server implements ServerInterface {

	Integer port;
	
	public Server() {
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
