package interfaces;

import java.rmi.Remote;

import classes.Message;

public interface IClient extends Remote {
	public void receive(Message m);
}
