package com.anntie.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
	private static UsersList list = new UsersList();
    private static ChatHistory chatHistory = new ChatHistory();
	
	public static void main(String[] args) {
		try {
			@SuppressWarnings("resource")
			ServerSocket socketListener = new ServerSocket(Config.PORT);
			System.out.println("Starting SC Server on port " + Config.PORT + "...");
			System.out.println("Success. Waiting for clients.");
			while(true) {
				Socket client = null;
				while(client == null) {
					client = socketListener.accept();
				}
				new ClientThread(client);
			}
		} catch (SocketException e) {
			System.out.println("Socket exception:");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public synchronized static UsersList getUserList() {
        return list;
    }

    public synchronized static ChatHistory getChatHistory() {
        return chatHistory;
    }
}
