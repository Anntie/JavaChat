package com.anntie.client;

import java.io.IOException;
import java.io.ObjectInputStream;

import com.anntie.message.Message;
import com.anntie.message.Ping;

public class SocketInputThread extends Thread implements Runnable {
	final private ObjectInputStream ois;
	private int packageInCounter = 0;
	private int packageOutCounter = 0;
	private static boolean isFinished = false;
	

    public SocketInputThread(ObjectInputStream o) {
        this.ois = o;
    }

    @Override
    public void run() {
    	try {
			Message msg;
			while(packageInCounter == packageOutCounter && isFinished == false) {
				msg = (Message) ois.readObject();
				if (msg instanceof Ping) {
					this.packageInCounter++;
					try {
						Client.ping(new Ping());
						this.packageOutCounter++;
					} catch(IOException e) {
						e.printStackTrace();			
					}
				}
				else {
					String completedMsg = "[" + msg.getLogin() + "] " + msg.getMessage();
					System.out.println(completedMsg);
				}
	        }
			SocketOutputThread.finish();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    public static void finish() {
    	isFinished = true;
    }
}
