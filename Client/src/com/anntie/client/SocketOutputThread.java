package com.anntie.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;

import com.anntie.message.Message;

public class SocketOutputThread extends Thread implements Runnable {
	final private ObjectOutputStream oos;
	private static boolean isFinished = false;
	
	public SocketOutputThread(ObjectOutputStream o) {
		this.oos = o;
	}
	
	@Override
	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			while(isFinished == false) {
				String outMsg = reader.readLine();
				Message msg = new Message(Client.LOGIN, outMsg);
				oos.writeObject(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public static void finish() {
    	isFinished = true;
    }
}
