package com.zm.sys.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 死信,延迟队列
 * @author Administrator
 *
 */
@Configuration
public class DeadQueueConfig {
	
	/**
	 * 动态声明queue、exchange、routing
	 * @param conn
	 * @return
	 */
	@Bean
	public RabbitAdmin rabbitAdmin(ConnectionFactory conn){
		RabbitAdmin rabbitAdmin = new RabbitAdmin(conn);
		 //声明死信队列（Fanout类型的exchange）
		Queue deadQueue = queue("dead.queue");
		//死信交换机
		FanoutExchange fexchange = new FanoutExchange("deal.fexchange");
		rabbitAdmin.declareQueue(deadQueue);
		rabbitAdmin.declareExchange(fexchange);
		rabbitAdmin.declareBinding(BindingBuilder.bind(deadQueue).to(fexchange));
		
		//定义一个添加积分队列
		Queue jfQueue = new Queue("jf.queue", true);
		DirectExchange jfExchange = new DirectExchange("jf.exchange", true, false);
		rabbitAdmin.declareQueue(jfQueue);
		rabbitAdmin.declareExchange(jfExchange);
		rabbitAdmin.declareBinding(BindingBuilder.bind(jfQueue).to(jfExchange).with("jf.router"));
		
		return rabbitAdmin;
	}
	
	private Queue queue(String name){
		Map<String, Object> args = new HashMap<>();
		 // 设置死信队列
		// x-dead-letter-exchange 声明了队列里的死信转发到的DLX名称，
        args.put("x-dead-letter-exchange", "jf.exchange");
     // x-dead-letter-routing-key 声明了这些死信在转发时携带的 routing-key 名称。
        args.put("x-dead-letter-routing-key", "jf.router");
        // 设置消息的过期时间， 单位是毫秒
        args.put("x-message-ttl", 5000);
        
        // 是否持久化
        boolean durable = true;
        // 仅创建者可以使用的私有队列，断开后自动删除
        boolean exclusive = false;
        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = false;
        
		Queue queue = new Queue(name, durable, exclusive, autoDelete, args);
		return queue;
	}
}
