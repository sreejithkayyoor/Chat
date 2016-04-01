package main.chat;
import java.io.*;
import java.net.*;
import gui.chat.ClientGUI;

public class ChatClient implements Runnable{
	private static String messageOut;
	private static String messageIn;
	private static boolean flag = true;
	private Thread thread = null;
	private Socket socket = null;
	private PrintStream printStream = null;
	private ClientGUI clientGUI = new ClientGUI();
	public ChatClient() {
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
//			do{
//				messageOut = null;
//				messageIn = null;
////				System.out.println("Enter message for server OR type log out to terminate operation\n");
//				InputStreamReader ir = new InputStreamReader(System.in);
//				BufferedReader br = new BufferedReader(ir);
//				messageOut = br.readLine();																	//Reads message from console to send to server.
//				printStream.println(messageOut);															//Sends message to server.
//				if(messageOut.equals("logout")){
//					flag = false;
//					socket.close();
//				}
//				else{
//					InputStreamReader iReader = new InputStreamReader(socket.getInputStream());
//					BufferedReader bReader = new BufferedReader(iReader);
//					messageIn = bReader.readLine();
//					clientGUI.displayMessage(messageIn);															//Prints message from server.
//				}
//				
//			}while(flag);
			while(flag){
				InputStreamReader iReader = new InputStreamReader(socket.getInputStream());
				BufferedReader bReader = new BufferedReader(iReader);
				messageIn = bReader.readLine();
				clientGUI.displayMessage(messageIn);
			}
		}catch (IOException e) {
			System.out.println("Server is offline..!\nPlease restart application and try again..!");		//Server gone offline, so IOEception while writing
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void sendMessage(String message){
		try { 
			printStream = new PrintStream(socket.getOutputStream());
			printStream.println(message);
		} catch (Exception e) {
			System.out.println("Can't send Message.. Please try again..!");
			clientGUI.displayMessage("Can't send Message.. Please try again..!");
		}
	}

}
