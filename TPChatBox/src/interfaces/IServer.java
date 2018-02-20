package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import classes.Client;
import classes.Message;

public interface IServer extends Remote {
	public void register(Client c) throws RemoteException;
	public boolean send(Message m);
	public void disconnect(Client c);
}