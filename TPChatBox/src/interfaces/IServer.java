package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import classes.Client;
import classes.Message;

public interface IServer extends Remote {
	public void register(IClient c, String s) throws RemoteException;
	public void connect(IClient c, String s) throws RemoteException;
	public boolean send(IMessage m) throws RemoteException;
	public void disconnect(IClient c) throws RemoteException;
}
