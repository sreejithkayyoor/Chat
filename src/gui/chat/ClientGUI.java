//GUI for ChatClient
package gui.chat;

import main.chat.ChatClient;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientGUI {

	private JFrame frmChatClient;
	private JTextField sendMsg;
	private JTextField txtLogin;
	private TextArea incomingMsg;
	private TextArea txtOnline;
	private JButton btnSend;
	private JButton btnLogin;
	private static boolean loginFlag = true;
	private static boolean nameExists = false;
	private static ChatClient client;
	private JLabel lblExistingLogin;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientGUI window = new ClientGUI();
					window.frmChatClient.setVisible(true);
					client = new ChatClient(window);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChatClient = new JFrame();
		frmChatClient.setFont(new Font("Bodoni MT Black", Font.PLAIN, 12));
		frmChatClient.setTitle("Chat Client");
		frmChatClient.setBounds(100, 100, 867, 396);
		frmChatClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChatClient.getContentPane().setLayout(null);
		
		JLabel lblMessage = new JLabel("Message");
		lblMessage.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMessage.setBounds(167, 11, 71, 23);
		frmChatClient.getContentPane().add(lblMessage);
		
		JLabel lblOnline = new JLabel("Online");
		lblOnline.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOnline.setBounds(711, 15, 46, 14);
		frmChatClient.getContentPane().add(lblOnline);
		
		incomingMsg = new TextArea();
		incomingMsg.setBackground(SystemColor.controlLtHighlight);
		incomingMsg.setEditable(false);
		//incomingMsg.setToolTipText("Messages");
		incomingMsg.setBounds(32, 44, 413, 195);
		frmChatClient.getContentPane().add(incomingMsg);
		
		txtOnline = new TextArea();
		txtOnline.setBackground(SystemColor.info);
		txtOnline.setEditable(false);
		txtOnline.setBounds(657, 44, 165, 195);
		frmChatClient.getContentPane().add(txtOnline);
		
		sendMsg = new JTextField();
		sendMsg.setToolTipText("Type Message Here");
		sendMsg.setBounds(128, 274, 317, 20);
		frmChatClient.getContentPane().add(sendMsg);
		sendMsg.setColumns(10);
		
		btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!sendMsg.getText().equals("")){
					client.sendMessage(sendMsg.getText());
				}
			}
		});
		btnSend.setToolTipText("Send");
		btnSend.setBounds(455, 273, 89, 23);
		frmChatClient.getContentPane().add(btnSend);
		btnSend.setEnabled(false);
		
		txtLogin = new JTextField();
		txtLogin.setToolTipText("Enter Login Name");
		txtLogin.setBounds(128, 326, 254, 20);
		frmChatClient.getContentPane().add(txtLogin);
		txtLogin.setColumns(10);
		
		btnLogin = new JButton("Login");
		btnLogin.setToolTipText("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(loginFlag){
					//String a = (btnLogin.getText().equals("Login"))? "Logout": "Login";					
					btnLogin.setText("Logout");
					incomingMsg.append("Login Successfull\n");
					btnSend.setEnabled(true);
					
				}else{
					if(nameExists){					
						lblExistingLogin.setVisible(true);
					}
					else{
						
					}
				}
			}			
		});
		btnLogin.setBounds(392, 325, 71, 23);
		frmChatClient.getContentPane().add(btnLogin);
		
		JLabel lblTypeMessage = new JLabel("Type Message");
		lblTypeMessage.setBounds(32, 277, 71, 14);
		frmChatClient.getContentPane().add(lblTypeMessage);
		
		JLabel lblEnterLoginName = new JLabel("Enter Login Name");
		lblEnterLoginName.setBounds(32, 329, 89, 14);
		frmChatClient.getContentPane().add(lblEnterLoginName);
		
		lblExistingLogin = new JLabel("Login already exists. Enter a new one.");
		lblExistingLogin.setLabelFor(txtLogin);
		lblExistingLogin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		lblExistingLogin.setForeground(Color.RED);
		lblExistingLogin.setBounds(184, 305, 199, 14);
		lblExistingLogin.setVisible(false);
		frmChatClient.getContentPane().add(lblExistingLogin);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(473, 325, 71, 23);
		frmChatClient.getContentPane().add(btnLogout);
	}
	public void displayMessage(String msg){
		incomingMsg.append(msg+"\n");
	}
}
