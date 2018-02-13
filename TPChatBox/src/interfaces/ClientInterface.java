package interfaces;

import java.rmi.Remote;

import classes.Message;

public interface ClientInterface extends Remote {
	public void receive(Message m);
}
