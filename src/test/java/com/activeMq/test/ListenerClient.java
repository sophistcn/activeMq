package com.activeMq.test;

public class ListenerClient {
	public static void main(String[] args) {
		Listener lis  = new Listener();
		lis.startReceiving("hi");
	}
}
