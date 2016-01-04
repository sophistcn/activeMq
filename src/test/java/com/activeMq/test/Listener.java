package com.activeMq.test;

import java.net.URI;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Listener {
	public void startReceiving(String textMessage) {
		try {
			String userName = "admin";
			String password = "admin";
			URI brokerURL = new URI("tcp://127.0.0.1:61616");

			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(userName, password, brokerURL);

			Connection connection = connectionFactory.createConnection();
			connection.start();

			Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			Destination queue = session.createQueue("Test.foo");

			MessageConsumer consumer = session.createConsumer(queue);


			MessageListener messageListener =new MessageListener() {

				@Override
				public void onMessage(Message msg) {
					if (msg instanceof TextMessage) {
						TextMessage txt = (TextMessage) msg;
						try {
							System.out.println("message received" + txt.getText());
						} catch (JMSException e) {
							System.out.println("error receiving message");
						}
					}

				}
			};
			
			consumer.setMessageListener(messageListener);
			
			System.in.read();
			consumer.close();
			session.close();
			connection.close();
			
		} catch (Exception e) {

		} finally {
		}
	}
}
