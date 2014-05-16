package com.anntie.message;


public class Ping extends Message {
	private static final long serialVersionUID = 3915186442090210103L;

	public Ping() {
        super("ping", "ping");
    }
}
