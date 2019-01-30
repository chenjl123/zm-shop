package com.zm.sys.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueSendConfig {
	
	@Bean(name="email")
	public Queue queue(){
		return new Queue("email.queue");
	}
	
	@Bean(name="emails")
	public Queue queueMails(){
		return new Queue("email.queues");
	}
	
	//定义一个交换机
	@Bean
	public TopicExchange exchange(){
		return new TopicExchange("exchange");
	}
	
	//绑定消息队列到交互机，指定路由键one.queue  只有发送这个路由键，才会发送消息到email.queue队列上
	@Bean
	Binding bindingExchangeMessage(@Qualifier("email") Queue queue, TopicExchange exchange){
		return BindingBuilder.bind(queue).to(exchange).with("one.queue");
	}
	
	@Bean
	Binding bindingExchangeMessages(@Qualifier("emails") Queue queue, TopicExchange exchange){
		return BindingBuilder.bind(queue).to(exchange).with("one.#");  //*表示一个词,#表示零个或多个词
	}
}
