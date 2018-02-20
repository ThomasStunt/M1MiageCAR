package launch;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import classes.Server;
import interfaces.IServer;

public class Lauch {

	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
			IServer serv = new Server();
			Naming.bind("ChatBox", serv);
		} catch(Exception e) {
			System.err.println(e);
		}
	}

}
