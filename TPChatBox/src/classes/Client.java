package classes;

import java.util.UUID;

import interfaces.ClientInterface;

public class Client implements ClientInterface {

	String login = UUID.randomUUID().toString();
	
	public Client() {
		
	}
	
	public void receive(Message m) {
		System.out.println(m);
	}

	public String getLogin() {
		return login;
	}
	
}
