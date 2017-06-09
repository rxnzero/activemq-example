package com.dhlee.activemq.queue;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 *
 */
public class App 
{
	public static void main(String[] args) {
	    // init spring context
	    ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");
	         
	    // get bean from context
	    JmsMessageSender jmsMessageSender = (JmsMessageSender)ctx.getBean("jmsMessageSender");
	         
	    // send to default destination 
	    jmsMessageSender.send("hello JMS 요청");
	         
	    // send to a code specified destination
	    Queue queue = new ActiveMQQueue("RequestQ-1");
	    jmsMessageSender.send(queue, "hello Another Queue 요청");
	   
	    try {
			Thread.sleep(100 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	    // close spring application context
	    ((ClassPathXmlApplicationContext)ctx).close();
	  }

}
