package com.dhlee.activemq.queue;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class JmsAnotationListener {
	  @JmsListener(destination="ResponseQ") //  read queue
	  @SendTo("DeadLetter") //  response queue
	  public String processMessage(String text) {
	    System.out.println("JmsAnotationListener Received: " + text);
	    return "ACK from JmsAnotationListener";
	  }

}
