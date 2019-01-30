package com.zm.msg.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MailService {
	
	@RabbitListener(queues="email.queue")
	public void reviceQueue(String str){
		System.out.println("one:" + str);
	}
	
	@RabbitListener(queues="email.queues")
	public void reviceQueues(String str){
		System.out.println("two:" + str);
	}
	
	@RabbitListener(queues="queue.a")
	public void fanoutExchangeMessageA(String str){
		System.out.println("queueA:" + str);
	}
	
	@RabbitListener(queues="queue.b")
	public void fanoutExchangeMessageB(String str){
		System.out.println("queueB:" + str);
	}
	
	@RabbitListener(queues="queue.c")
	public void fanoutExchangeMessageC(String str){
		System.out.println("queueC:" + str);
	}
}
