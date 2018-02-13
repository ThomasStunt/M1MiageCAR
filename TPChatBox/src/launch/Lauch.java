package launch;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import classes.Server;

public class Lauch {

	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
			Server s = new Server();
			Naming.bind("ChatBox", s);
		} catch(Exception e) {
			System.err.println(e);
		}
	}

}
