package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IServer extends Remote {
	public void register(IClient c, String s) throws RemoteException;
	public void connect(IClient c, String s) throws RemoteException;
	public boolean send(IMessage msg) throws RemoteException;
	public void disconnect(IClient c) throws RemoteException;
	
	public List<IClient> getClientsConnected() throws RemoteException;
}
