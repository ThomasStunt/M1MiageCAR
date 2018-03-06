package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import interfaces.IServer;

public class HomePanel extends JPanel {
	private static final long serialVersionUID = -1388708028473335100L;

	protected ClientUI mainFrame;
	protected IServer s;
	
	protected JButton connectButton;
	protected JButton registerButton;
	
	public HomePanel(ClientUI mFrame, IServer serv) {
		this.mainFrame = mFrame;
		this.s = serv;
		
		registerButton = new JButton("Register");
		registerButton.setPreferredSize(new Dimension(150,28));
		
		GridBagConstraints gbc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		
		gbc.gridx = 0;
		gbc.insets = new Insets(10, 0, 10, 0);
		
		this.add(configConnectButton(), gbc);
		this.add(configRegisterButton(), gbc);
	}
	
	public JButton configConnectButton() {
		connectButton = new JButton("Connect");
		connectButton.setPreferredSize(new Dimension(150,28));
		
		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.switchPanel(new ConnexionPanel(mainFrame, s));
			}
		});

		connectButton.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			
			public void keyReleased(KeyEvent e) {
			}
			
			public void keyPressed(KeyEvent e) {
				if(e.equals(KeyEvent.VK_ENTER))
					connectButton.doClick();
			}
		});
		
		return connectButton;
	}
	
	public JButton configRegisterButton() {
		registerButton = new JButton("Register");
		registerButton.setPreferredSize(new Dimension(150,28));
		
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.switchPanel(new InscriptionPanel(mainFrame, s));
			}
		});

		registerButton.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			
			public void keyReleased(KeyEvent e) {
			}
			
			public void keyPressed(KeyEvent e) {
				if(e.equals(KeyEvent.VK_ENTER))
					registerButton.doClick();
			}
		});
		
		return registerButton;
	}
	
	public ClientUI getMainFrame() {
		return mainFrame;
	}
}
