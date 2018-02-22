package launch;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.Scanner;

import classes.Client;
import classes.Message;
import interfaces.IClient;
import interfaces.IMessage;
import interfaces.IServer;

@SuppressWarnings("deprecation")
public class ClientLaunch {
	
	static IServer remo;
	static IClient connected;
	
	public static void main(String[] args) {
		try {
			remo = (IServer) Naming.lookup("rmi://localhost:1099/ChatBox");
		} catch (Exception e) {
			e.printStackTrace();
		}

		connected = null;
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					remo.disconnect(connected);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
		
		try {
			
			System.setProperty("java.security.policy", "file:./permissions.policy");
			System.setSecurityManager(new RMISecurityManager());
			
			System.out.println("[SUCCESS] Client launched.");
			System.out.println("\nPress 1 to connect / 2 to register.");
			
			Scanner sc = new Scanner(System.in);
			
			String choice;
			
			while(!(choice = sc.nextLine()).contentEquals("")) {
				if(choice.contentEquals("1")) {
					System.out.println("Login : ");
					String log = sc.nextLine();
					System.out.println("Password : ");
					String passwd = sc.nextLine();
					connected = new Client(log, passwd);
					if(!remo.connect(connected, passwd)) {
						System.out.println("[ERROR] User "+connected.getLogin()+" is already connected.");
						connected = null;
						System.exit(0);
					}
				}
				else if(choice.contentEquals("2")) {
					System.out.println("Login to register : ");
					String log = sc.nextLine();
					System.out.println("Password : ");
					String passwd = sc.nextLine();
					connected = new Client(log, passwd);
					remo.register(connected, passwd);
					break;
				}
				else
					System.out.println("[ERROR] Wrong input. ");
			}

			System.out.println("\n\n\n\n\n\n");
			
			System.out.println("[SUCCESS] You are now connected.");
			System.out.println("[INFO] You can now chat with other users.");
			System.out.println("[INFO] Write 'DC' to disconnect.");
			
			String msgToSend;
			
			IMessage msg;
			
			while(!(msgToSend = sc.nextLine()).contentEquals("")) {
				if(msgToSend.contentEquals("DC")) {
					remo.disconnect(connected);
					break;
				} else {
					msg = new Message(connected, msgToSend);
					remo.send(msg);
				}
			}
			
			sc.close();
			Runtime.getRuntime().exit(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
