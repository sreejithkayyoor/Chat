package main.chat;
import java.io.*;
import java.net.*;
import gui.chat.ClientGUI;

public class ChatClient implements Runnable{
	private static String messageIn;
	private static boolean flag = true;
	private Thread thread = null;
	private Socket socket = null;
	private PrintStream printStream = null;
	private ClientGUI clientGUI ;
	public ChatClient(ClientGUI gui) {
		clientGUI = gui;
		try {
			socket = new Socket("localhost",444);
			start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	public void start(){
		thread = new Thread(this);
		thread.start();
	}
	public void run() {													//Socket with port '444' and ip localhost
		
		try {		
			while(flag){
				InputStreamReader iReader = new InputStreamReader(socket.getInputStream());
				BufferedReader bReader = new BufferedReader(iReader);
				messageIn = bReader.readLine();
				if(messageIn.equals(null)){
					flag = false;
				}else{
					clientGUI.displayMessage(messageIn);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void sendMessage(String message){
		try { 
			printStream = new PrintStream(socket.getOutputStream());
			printStream.println(message);
		}catch (IOException e) {
			System.out.println("Server is offline..!\nPlease restart application and try again..!");		//Server gone offline, so IOEception while writing
			clientGUI.displayMessage("Can't send Message.. Please restart application and try again..!");
		}		
		catch (Exception e) {
			System.out.println("Can't send Message.. Please try again..!");			
		}
	}

}
