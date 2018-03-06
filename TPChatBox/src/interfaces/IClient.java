package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClient extends Remote {
	public void receive(IMessage m, Boolean isPrivate) throws RemoteException;

	public String getLogin() throws RemoteException;
}
