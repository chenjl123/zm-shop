package com.zm.sys.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 广播式，订阅式
 * @author Administrator
 *
 */
@Configuration
public class FanoutExchangeConfig {

	@Bean(name="queueA")
	public Queue queueA(){
		return new Queue("queue.a");
	}
	
	@Bean(name="queueB")
	public Queue queueB(){
		return new Queue("queue.b");
	}
	
	@Bean(name="queueC")
	public Queue queueC(){
		return new Queue("queue.c");
	}
	
	@Bean
	public FanoutExchange fanoutExchange(){
		return new FanoutExchange("fanoutExchange");
	}
	
	//绑定消息队列到交换机
	@Bean
	public Binding bindingExchangeA(@Qualifier("queueA") Queue queueA, FanoutExchange fanoutExchange){
		return BindingBuilder.bind(queueA).to(fanoutExchange);
	}
	
	@Bean
	public Binding bindingExchangeB(@Qualifier("queueB") Queue queueB, FanoutExchange fanoutExchange){
		return BindingBuilder.bind(queueB).to(fanoutExchange);
	}
	
	@Bean
	public Binding bindingExchangeC(@Qualifier("queueC") Queue queueC, FanoutExchange fanoutExchange){
		return BindingBuilder.bind(queueC).to(fanoutExchange);
	}
	
}
