package launch;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import classes.Server;
import interfaces.IServer;

public class Lauch {

	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
			IServer serv = new Server();
			Naming.bind("ChatBox", serv);
			System.out.println("[SUCCESS] Server is now launched.");
		} catch (RemoteException | MalformedURLException | AlreadyBoundException e) {
			System.out.println("[ERROR] "+e.getMessage());
		}
	}

}
