package com.anntie.message;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

	private static final long serialVersionUID = -1853205656682408965L;
	private String login;
    private String message;
    private String[] users;
    private Date time;
    
    //Client constructor
    public Message(String login, String message){
        this.login = login;
        this.message = message;
        this.time = java.util.Calendar.getInstance().getTime();
    }

    //Server constructor
    public Message(String login, String message, String[] users){
        this.login = login;
        this.message = message;
        this.time = java.util.Calendar.getInstance().getTime();
        this.users = users;
    }

    public void setOnlineUsers(String[] users) {
        this.users = users;
    }

    public String getLogin() {
        return this.login;
    }

    public String getMessage() {
        return this.message;
    }

    public String[] getUsers() {
        return this.users;
    }

    public String getDate(){
    	Integer tm = (int) time.getTime();
    	return tm.toString();
    }
}