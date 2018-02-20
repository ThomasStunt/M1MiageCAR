package launch;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.Scanner;

import classes.Client;
import interfaces.IServer;

@SuppressWarnings("deprecation")
public class ClientLaunch {
	public static void main(String[] args) {
		try {
			
			System.setProperty("java.security.policy", "file:./permissions.policy");
			System.setSecurityManager(new RMISecurityManager());
			
			System.out.println("[SUCCESS] Client launched.");
			System.out.println("\nPress 1 to connect / 2 to register.");
			
			IServer remo = (IServer) Naming.lookup("rmi://localhost:1099/ChatBox");
			
			Scanner sc = new Scanner(System.in);
			
			String choice;
			
			while(!(choice = sc.nextLine()).contentEquals("")) {
				if(choice.contentEquals("1")) {
					System.out.println("Login : ");
					String log = sc.nextLine();
					System.out.println("Password : ");
					String passwd = sc.nextLine();
					remo.connect(new Client(log, passwd), passwd);
					break;
				}
				else if(choice.contentEquals("2")) {
					System.out.println("Login to register : ");
					String log = sc.nextLine();
					System.out.println("Password : ");
					String passwd = sc.nextLine();
					remo.register(new Client(log, passwd), passwd);
					break;
				}
				else
					System.out.println("[ERROR] Wrong input. ");
			}
			
			System.out.println("[SUCCESS] You are now connected.");
			
			sc.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
