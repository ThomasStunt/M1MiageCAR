package classes;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import gui.ChatPanel;
import interfaces.IClient;
import interfaces.IMessage;

public class Client extends UnicastRemoteObject implements IClient {

	private static final long serialVersionUID = 5702976698815591119L;

	protected String login;
	protected String passwd;
	protected ChatPanel p;

	public Client(String login, String passwd, ChatPanel pan) throws RemoteException {
		super();
		this.login = login;
		this.passwd = passwd;
		this.p = pan;
	}

	public void receive(IMessage m, Boolean isPrivate) throws RemoteException {
		if (isPrivate) {
			p.getChat().append(m.toString() + "\n");
			p.getChat().setCaretPosition(p.getChat().getDocument().getLength());
		} else {
			p.getChat().append(m.toString() + "\n");
			p.getChat().setCaretPosition(p.getChat().getDocument().getLength());
		}
	}

	public String getLogin() {
		return login;
	}

}
