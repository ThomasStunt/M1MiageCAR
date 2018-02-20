package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import classes.Message;

public interface IClient extends Remote {
	public void receive(IMessage m) throws RemoteException;
}
