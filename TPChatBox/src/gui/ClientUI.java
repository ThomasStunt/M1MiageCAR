package gui;

import java.awt.Dimension;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import interfaces.IServer;

public class ClientUI extends JFrame {

	private static final long serialVersionUID = 3115028229497297295L;

	protected IServer remo;
	
	public ClientUI() {
		super();
		
		System.setProperty("java.security.policy", "file:./permissions.policy");
		System.setSecurityManager(new RMISecurityManager());
		
		System.out.println("[SUCCESS] Client launched.");
		
		try {
			remo = (IServer) Naming.lookup("rmi://localhost:1099/ChatBox");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		this.setContentPane(new HomePanel(this, remo));
		this.setPreferredSize(new Dimension(400,300));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new ClientUI();
	}
	
	public void switchPanel(JPanel p) {
		this.setVisible(false);
		this.getContentPane().removeAll();
		this.getContentPane().add(p);
		this.pack();
		this.setVisible(true);
	}
	
}
