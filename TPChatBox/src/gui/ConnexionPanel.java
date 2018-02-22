package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import classes.Client;
import interfaces.IClient;
import interfaces.IServer;

public class ConnexionPanel extends JPanel {
	private static final long serialVersionUID = -1473271216816795624L;
	
	protected ClientUI mainFrame;
	protected IServer serv;
	
	protected JLabel logLabel, passLabel;
	protected JTextField logTF;
	protected JPasswordField passTF;
	protected JButton registerButton;
	
	public ConnexionPanel(ClientUI mFrame, IServer s) {
		this.mainFrame = mFrame;
		this.serv = s;
		
		logLabel = new JLabel("Login : ");
		passLabel = new JLabel("Password : ");
		
		logTF = new JTextField();
		passTF = new JPasswordField();
		
		logTF.setPreferredSize(new Dimension(150,25));
		passTF.setPreferredSize(new Dimension(150,25));
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
	
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
		gbc.gridy = 1;
		
		this.add(logLabel, gbc);

		gbc.gridy = 2;
		gbc.insets = new Insets(15,0,0,0);
		gbc.anchor = GridBagConstraints.BASELINE;
		
		this.add(passLabel, gbc);
		
		gbc.gridx = 2;
		gbc.anchor = GridBagConstraints.BASELINE_TRAILING;
		gbc.gridy = 1;
		
		this.add(logTF, gbc);

		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.BASELINE;
		
		this.add(passTF, gbc);
		
		gbc.gridy = 3;
		gbc.insets = new Insets(20, 0, 0, 0);
		
		this.add(configRegisterButton(), gbc);
	}

	private JButton configRegisterButton() {
		registerButton = new JButton("Connect");
		
		registerButton.setPreferredSize(new Dimension(150,28));
		
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					IClient client = new Client(logTF.getText(), passTF.getText());
					serv.connect(client, passTF.getText());
					JOptionPane.showMessageDialog(null, "T'es co enculé");
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
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
	
}
