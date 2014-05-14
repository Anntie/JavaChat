package com.anntie.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.anntie.message.Message;

public class ChatHistory implements Serializable {
	private static final long serialVersionUID = -4270621483801380077L;
	private List<Message> history;

    public ChatHistory() {
        this.history = new ArrayList<Message>(Config.HISTORY_LENGHT);
    }

    public void addMessage(Message message){
        if (this.history.size() > Config.HISTORY_LENGHT){
            this.history.remove(0);
        }

        this.history.add(message);
    }

    public List<Message> getHistory(){
        return this.history;
    }

}
