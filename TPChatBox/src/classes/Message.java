package classes;

import java.rmi.RemoteException;
import java.util.Date;

import interfaces.IMessage;

public class Message implements IMessage {

	private static final long serialVersionUID = -8969578158052077387L;

	Client cl;
	Date dat;
	String cont;
	
	public Message(Client c, String content) {
		this.cl = c;
		this.dat = new Date();
		this.cont = content;
	}
	
	@Override
	public void setContent(String content) {
		this.cont = content;
	}

	@Override
	public void setSource(Client c) {
		this.cl = c;
	}

	@Override
	public String toString() {
		try {
			return cl.getLogin()+" ("+this.getTime()+") : "+this.cont;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public String getTime() {
		String res = "";
		int hours = dat.getHours();
		if(hours < 10) {
			res+="0";
			res+=hours;
		} else {
			res+=hours;
		}
		res+=":";
		int mins = dat.getMinutes();
		if(mins < 10) {
			res+="0";
			res+=mins;
		} else {
			res+=mins;
		}
		return res;
	}	
	
	public static void main(String[] args) {
		Message m = new Message(new Client(), "salut");
		System.out.println(m);
	}
}
