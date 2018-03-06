package classes;

import java.rmi.RemoteException;
import java.util.Date;

import interfaces.IClient;
import interfaces.IMessage;

public class Message implements IMessage {

	private static final long serialVersionUID = -8969578158052077387L;

	IClient cl;
	Date dat;
	String cont;
	Boolean priv;
	Boolean send;

	public Message(IClient c, String content, Boolean isPrivate) {
		this.cl = c;
		this.dat = new Date();
		this.cont = content;
		this.priv = isPrivate;
	}

	@Override
	public String getContent() {
		return this.cont;
	}

	@Override
	public void setContent(String content) {
		this.cont = content;
	}

	@Override
	public IClient getSource() {
		return cl;
	}

	@Override
	public void setSource(IClient c) {
		this.cl = c;
	}

	@Override
	public String toString() {
		if (!this.priv) {
			try {
				return cl.getLogin() + " (" + this.getTime() + ") : " + this.cont;
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		} else {
			try {
				return "[PM From : " + cl.getLogin() + " to " + this.cont.substring(1).split(" ")[0] + "] : " + this.cont.substring(cont.substring(1).split(" ")[0].length()+2);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	@SuppressWarnings("deprecation")
	public String getTime() {
		String res = "";
		int hours = dat.getHours();

		if (hours < 10) {
			res += "0" + hours;
		} else {
			res += hours;
		}

		res += ":";
		int mins = dat.getMinutes();

		if (mins < 10) {
			res += "0" + mins;
		} else {
			res += mins;
		}

		return res;
	}
}
