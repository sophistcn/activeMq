package com.activeMq.test;

import java.net.URI;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender{
	
	
	public void sendMessage(String textMessage) {
		try {
			String userName = "admin";
			String password = "admin";
			URI brokerURL = new URI("tcp://127.0.0.1:61616");
			
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(userName, password, brokerURL);  
			  
		    Connection connection = connectionFactory.createConnection();  
		    connection.start();  
		    
		    Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);  
		    Destination destination = session.createQueue("Test.foo");  

		    MessageProducer producer = session.createProducer(destination);  
		    producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		    
			TextMessage txt = session.createTextMessage(textMessage );
			
			producer.send(txt);
			connection.close();
			System.out.println("send msg: " + textMessage);
		} catch (Exception e) {
			
		}  finally {
		}
	}
}
