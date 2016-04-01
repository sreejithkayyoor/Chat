package main.chat;

import java.io.*;
import java.net.*;

public class ChatServerThread implements Runnable{
	private Socket socket = null;
	private Thread thread = null;
	private BufferedReader br = null;
	private PrintStream ps = null;
	private InputStreamReader ir = null;
	private InputStreamReader isr = null;
	private BufferedReader brdr = null;
	private String login = null;
	private String message = null;
	private static boolean flag = false;
	
	
	ChatServerThread(Socket clientSocket){ 
		socket = clientSocket;
		thread = new Thread(this);
		thread.start();
		}
	
	public void run(){
		try {			
			ps = new PrintStream(socket.getOutputStream());
			while(!flag){
				isr = new InputStreamReader(socket.getInputStream());
				brdr = new BufferedReader(isr);
				login = brdr.readLine();
				boolean check = checkList(login);
				if(check){
					ps.println(true);
					flag =true;
				}
				else{
					ps.println(false);
				}
			}
			while(true&&flag){
				ir = new InputStreamReader(socket.getInputStream());
				br = new BufferedReader(ir);
				message = br.readLine();
				System.out.println("Recieved Message is: "+message);	//Message from client							
					ps.println("Message is Recieved");
				
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	private boolean checkList(String login){
		if(ChatServer.clientList.containsKey(login)){
			System.out.println("User Exists");
			return false;
		}			
		else{
			ChatServer.clientList.put(login, socket);
			return true;
		}			
	}
}
