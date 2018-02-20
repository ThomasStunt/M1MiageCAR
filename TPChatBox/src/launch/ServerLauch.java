package launch;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import classes.Server;
import interfaces.IServer;

public class ServerLauch {

	public static void main(String[] args) {
		
		try {
			IServer serv = new Server();
			
			LocateRegistry.createRegistry(1099);
			LocateRegistry.getRegistry().bind("ChatBox", serv);
			
			System.out.println("[SUCCESS] Server is now launched.");
			
		} catch (RemoteException | AlreadyBoundException e) {
			
			System.out.println("[ERROR] "+e.getMessage());
			
		}
	}

}
