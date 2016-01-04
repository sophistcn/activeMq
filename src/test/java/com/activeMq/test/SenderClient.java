package com.activeMq.test;

import java.util.Date;

public class SenderClient {
	public static void main(String[] args) {
		Sender send = new Sender();
		send.sendMessage("hello" + new Date());
	}
}
