package classes;

import java.rmi.RemoteException;

import interfaces.IServer;

public class Server implements IServer {

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
