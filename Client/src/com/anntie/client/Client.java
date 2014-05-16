package com.anntie.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.anntie.message.ChatHistory;
import com.anntie.message.Message;
import com.anntie.message.Ping;

public class Client {
	public static String LOGIN;
	private static ObjectOutputStream oos;
	
	public static void main(String[] args) {
		if(args.length == 3) {
			System.out.println("Starting SC Client...");
			String ip = args[0];
			LOGIN = args[2];
			int port = Integer.parseInt(args[1]);
			try {
				
				System.out.println("Connecting to server " + ip + " on port " + port);
				@SuppressWarnings("resource")
				Socket connection = new Socket(ip, port);
				oos = new ObjectOutputStream(connection.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(connection.getInputStream());
				handShake(ois, oos);
				
				Thread threadIn = new Thread(new SocketInputThread(ois));
				Thread threadOut = new Thread(new SocketOutputThread(oos));
				threadOut.start();
				threadIn.start();
				
				
			} catch (IOException e) {
				System.out.println("Couldn't connect to server: " + e.getMessage());
				e.printStackTrace();
			}
		}
		else System.out.println("Usage: java server.jar <ip> <port> <login>");
	}
	
	private static void handShake(ObjectInputStream ois, ObjectOutputStream oos) {
		Message msg = new Message(Client.LOGIN, Config.HELLO_MESSAGE);
		try {
			oos.writeObject(msg);
			ChatHistory history = (ChatHistory) ois.readObject();
			for(Message m : history.getHistory()) {
				String s = "[" + m.getLogin() + "] " + m.getMessage();
				System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void ping(Ping p) throws IOException {
		oos.writeObject(p);
	}
}
