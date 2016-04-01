package gui.chat;

import main.chat.ChatServer;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ServerGUI {

	private JFrame frmServer;
	private JTextField txtEnterMessage;
	private JButton btnStartServer;
	private JButton btnStopServer;
	private TextArea txtOnline;
	private TextArea txtMessages;
	private ChatServer server;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerGUI window = new ServerGUI();
					window.frmServer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ServerGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmServer = new JFrame();
		frmServer.setTitle("Chat Server");
		frmServer.setBounds(100, 100, 915, 348);
		frmServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServer.getContentPane().setLayout(new BoxLayout(frmServer.getContentPane(), BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		frmServer.getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblMessage = new JLabel("Message");
		lblMessage.setForeground(new Color(204, 51, 102));
		GridBagConstraints gbc_lblMessage = new GridBagConstraints();
		gbc_lblMessage.insets = new Insets(0, 0, 5, 5);
		gbc_lblMessage.gridx = 1;
		gbc_lblMessage.gridy = 0;
		panel.add(lblMessage, gbc_lblMessage);
		
		JLabel lblOnline = new JLabel("Online");
		lblOnline.setForeground(new Color(204, 51, 0));
		GridBagConstraints gbc_lblOnline = new GridBagConstraints();
		gbc_lblOnline.gridwidth = 3;
		gbc_lblOnline.insets = new Insets(0, 0, 5, 5);
		gbc_lblOnline.gridx = 4;
		gbc_lblOnline.gridy = 0;
		panel.add(lblOnline, gbc_lblOnline);
		
		txtMessages = new TextArea();
		txtMessages.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		txtMessages.setEditable(false);
		txtMessages.setBackground(new Color(255, 255, 204));
		GridBagConstraints gbc_txtMessages = new GridBagConstraints();
		gbc_txtMessages.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMessages.insets = new Insets(0, 0, 5, 5);
		gbc_txtMessages.gridx = 1;
		gbc_txtMessages.gridy = 1;
		panel.add(txtMessages, gbc_txtMessages);
		
		txtOnline = new TextArea();		
		txtOnline.setEditable(false);
		txtOnline.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		txtOnline.setBackground(new Color(255, 255, 204));
		GridBagConstraints gbc_txtOnline = new GridBagConstraints();
		gbc_txtOnline.insets = new Insets(0, 0, 5, 5);
		gbc_txtOnline.gridwidth = 11;
		gbc_txtOnline.fill = GridBagConstraints.BOTH;
		gbc_txtOnline.gridx = 3;
		gbc_txtOnline.gridy = 1;
		panel.add(txtOnline, gbc_txtOnline);
		
		txtEnterMessage = new JTextField();
		txtEnterMessage.setToolTipText("Enter message to be Broadcasted");
		txtEnterMessage.setBackground(new Color(204, 204, 204));
		GridBagConstraints gbc_txtEnterMessage = new GridBagConstraints();
		gbc_txtEnterMessage.gridwidth = 10;
		gbc_txtEnterMessage.insets = new Insets(0, 0, 5, 5);
		gbc_txtEnterMessage.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEnterMessage.gridx = 1;
		gbc_txtEnterMessage.gridy = 3;
		panel.add(txtEnterMessage, gbc_txtEnterMessage);
		txtEnterMessage.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.setBackground(UIManager.getColor("Button.background"));
		btnSend.setToolTipText("Click to Broadcast message");
		btnSend.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_btnSend = new GridBagConstraints();
		gbc_btnSend.gridwidth = 2;
		gbc_btnSend.insets = new Insets(0, 0, 5, 5);
		gbc_btnSend.gridx = 12;
		gbc_btnSend.gridy = 3;
		panel.add(btnSend, gbc_btnSend);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridwidth = 15;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 4;
		panel.add(separator, gbc_separator);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 13;
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 5;
		panel.add(panel_1, gbc_panel_1);
		
		btnStartServer = new JButton("Start Server");
		btnStartServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				server = new ChatServer();
				btnStartServer.setEnabled(false);
				btnStopServer.setEnabled(true);
				
			}
		});
		btnStartServer.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnStartServer.setBackground(UIManager.getColor("Button.background"));
		btnStartServer.setForeground(new Color(0, 0, 0));
		btnStartServer.setToolTipText("Click to Start Server");
		panel_1.add(btnStartServer);
		
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		panel_1.add(separator_1);
		
		JSeparator separator_7 = new JSeparator();
		panel_1.add(separator_7);
		
		JSeparator separator_2 = new JSeparator();
		panel_1.add(separator_2);
		
		JSeparator separator_5 = new JSeparator();
		panel_1.add(separator_5);
		
		JSeparator separator_3 = new JSeparator();
		panel_1.add(separator_3);
		
		JSeparator separator_6 = new JSeparator();
		panel_1.add(separator_6);
		
		JSeparator separator_4 = new JSeparator();
		panel_1.add(separator_4);
		
		btnStopServer = new JButton("Stop Server");
		btnStopServer.setBackground(UIManager.getColor("Button.background"));
		btnStopServer.setToolTipText("Click to Stop Server");
		btnStopServer.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnStopServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int value = JOptionPane.showConfirmDialog(null, "Sure you want to stop Server?", "Are you Sure?", JOptionPane.YES_NO_OPTION);
				System.out.println(value);
				if(value == 0){
					//Yes
					server.close();
					btnStartServer.setEnabled(true);
					btnStopServer.setEnabled(false);
				}else{
					//No
				}						
			}
		});
		panel_1.add(btnStopServer);
		btnStopServer.setEnabled(false);
	}

}
