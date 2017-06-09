package com.dhlee.activemq.queue;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;

@Service
/**
 * Listener Implement Spring SessionAwareMessageListener Interface
 *
 */
public class JmsMessageListener implements SessionAwareMessageListener<TextMessage> {

	@Override
	public void onMessage(TextMessage message, Session session) throws JMSException {
		// This is the received message
		System.out.println("JMSDestination: "+message.getJMSDestination());
		System.out.println("JmsMessageListener Receive: "+message.getText());
	    
		// response message send
		ActiveMQTextMessage textMessage = new ActiveMQTextMessage();
	    textMessage.setText("Seceive OK - " + message.getJMSCorrelationID());
	    System.out.println("Reply to: "+ message.getJMSReplyTo());
	    MessageProducer producer = session.createProducer(message.getJMSReplyTo());
	    producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
	    producer.send(textMessage);
	}

}
