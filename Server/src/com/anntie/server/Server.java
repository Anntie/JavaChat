package com.anntie.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import com.anntie.message.ChatHistory;

public class Server {
	private static UsersList list = new UsersList();
    private static ChatHistory chatHistory = new ChatHistory();
	
	public static void main(String[] args) {
		try {
			int port = Integer.parseInt(args[0]);
			if(args.length == 1) {
				@SuppressWarnings("resource")
				ServerSocket socketListener = new ServerSocket(port);
				System.out.println("Starting SC Server on port " + port + "...");
				System.out.println("Success. Waiting for clients.");
				while(true) {
					Socket client = null;
					while(client == null) {
						client = socketListener.accept();
					}
					new ClientThread(client);
				}
			}
			else System.out.println("Usage: java -jar server.jar <port>");
		} catch (SocketException e) {
			System.out.println("Socket exception:");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("Usage: java -jar server.jar <Integer port>");
		}
	}

	public synchronized static UsersList getUserList() {
        return list;
    }

    public synchronized static ChatHistory getChatHistory() {
        return chatHistory;
    }
}
