package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import classes.Message;
import interfaces.IMessage;
import interfaces.IServer;

public class ChatPanel extends JPanel {

	private static final long serialVersionUID = -3594048662277078127L;

	protected ClientUI mainFrame;
	protected IServer serv;
	
	protected JTextArea chatArea;
	protected JScrollPane chatPane;
	protected JTextField discussField;
	protected JButton sendButton;
	protected JButton disconnectButton;
	
	public ChatPanel(ClientUI mFrame, IServer s) {
		this.mainFrame = mFrame;
		this.serv = s;
		
		mFrame.setResizable(false);
		mFrame.setPreferredSize(new Dimension(450, 500));
		
		chatArea = new JTextArea(20,30);
		chatArea.setEditable(false);
		
		chatPane = new JScrollPane(chatArea);
		chatPane.setAutoscrolls(true);
		
		discussField = new JTextField();
		discussField.setPreferredSize(new Dimension(130, 25));
		discussField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendButton.doClick();
			}
		});
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridy = 1;
		
		this.add(chatPane, gbc);
		
		gbc.gridy = 2;
		gbc.gridx = 0;
		gbc.insets = new Insets(10, 0, 0, 0);
		
		this.add(discussField, gbc);
		
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.BASELINE;
		
		this.add(configSendButton(), gbc);
		
		gbc.gridy = 4;
		
		this.add(configDisconnectButton(), gbc);
	}
	
	private JButton configDisconnectButton() {
		disconnectButton = new JButton("Disconnect");
		
		disconnectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					serv.disconnect(mainFrame.getConnected());
					mainFrame.dispose();
					System.exit(0);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		return disconnectButton;
	}

	public JButton configSendButton() {
		sendButton = new JButton("Send");
		
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String msg = discussField.getText();
					Boolean isPrivate = false;
					if(msg.charAt(0) == '@')
						isPrivate = true;
					if(msg.contentEquals(""))
						return;
					else {
						IMessage iMsg = new Message(mainFrame.getConnected(), msg, isPrivate);
						serv.send(iMsg);
						discussField.setText("");
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		return sendButton;
	}
	
	public JTextArea getChat() {
		return chatArea;
	}
	
}
