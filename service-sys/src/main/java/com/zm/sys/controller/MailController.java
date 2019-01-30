package com.zm.sys.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
	
	@Autowired
	private AmqpTemplate templete;
	
	/**
	 * 简单队列发送
	 * @return
	 */
	@GetMapping("/queueSend")
	public String queueSend(){
		templete.convertAndSend("email.queue", "hello email");;
		return "success";
	}
	
	/**
	 * 绑定交换机上发送队列消息
	 * 交换机
	 * 路由键，当路由键为one.queues,  只有email.queues队列能收到消息， 当路由键为one.queue,则email.queue和email.queues队列都能收到消息
	 * 消息内容
	 * @return
	 */
	@GetMapping("/exchangeSend")
	public String exchangeSend(){
		templete.convertAndSend("exchange", "one.queue", "hello,rabbit");
		return "success";
	}
	
	@GetMapping("/fanoutExchangeSend")
	public String fanoutExchangeSend(){
		templete.convertAndSend("fanoutExchange", "", "hello fanout");
		return "success";
	}
}
