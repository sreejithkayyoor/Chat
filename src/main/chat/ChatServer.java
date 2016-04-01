package main.chat;
import java.net.*;
import java.security.KeyStore.Entry;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;

public class ChatServer implements Runnable{
	protected static HashMap<String, Socket> clientList = new HashMap<>();	
	private Thread thread = null;
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	private PrintStream ps = null;
	private boolean status = true;
	
	public static void main(String[] args) throws Exception{
		new ChatServer();
		
	}
	public ChatServer() {
		try {
			serverSocket = new ServerSocket(444);
			System.out.println("Server Started.\nWaiting for clients.");
			start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void start(){
		thread = new Thread(this);
		thread.start();
	}
	public void run(){		
		while(status){
			try {			
				socket = serverSocket.accept();
				System.out.println(socket);
				ps = new PrintStream(socket.getOutputStream());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Server Stopped");
				status = false;
			}
			new ChatServerThread(socket);
			
			
		}
	}
	public void close(){		
		if(socket !=null){
			System.out.println("Clients present");
			try {				
//				Iterator<HashMap.Entry<String, Socket>> iterator = clientList.entrySet().iterator();
//				while(iterator.hasNext()){
//					HashMap.Entry<String,Socket> entry = iterator.next();
//					Socket sock = entry.getValue();
//					sock.close();
//					}
				for(Socket sock: clientList.values()){
					sock.close();
				}
				serverSocket.close();				
			} catch (Exception e) {
				e.printStackTrace();	
				System.out.println("Can't Close socket");				
			}
		}
		else{
			System.out.println("No clients");
			try {				
				serverSocket.close();
			} catch (Exception e) {
				System.out.println("Can't Close socket");
			}
		}
	}
}
