package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClientUI extends JFrame {

	private static final long serialVersionUID = 3115028229497297295L;

	public ClientUI() {
		super();
		this.setContentPane(new ConnexionPanel());
		this.setPreferredSize(new Dimension(400,300));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new ClientUI();
	}
	
	private class ConnexionPanel extends JPanel {
		
		private static final long serialVersionUID = -1388708028473335100L;

		protected JButton connectButton;
		protected JButton registerButton;
		
		public ConnexionPanel() {
			registerButton = new JButton("Register");
			registerButton.setPreferredSize(new Dimension(150,28));
			
			GridBagConstraints gbc = new GridBagConstraints();
			this.setLayout(new GridBagLayout());
			
			gbc.gridx = 0;
			gbc.insets = new Insets(10, 0, 10, 0);
			
			this.add(configConnectButton(), gbc);
			this.add(registerButton, gbc);
		}
		
		public JButton configConnectButton() {
			connectButton = new JButton("Connect");
			connectButton.setPreferredSize(new Dimension(150,28));
			
			connectButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
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
		
	}
	
}
