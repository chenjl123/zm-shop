package com.zm.msg.service;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;


@Service
public class JfQueueListener {
	
	//添加积分消息监听
	@RabbitListener(queues = "jf.queue")
	public void jfAdd(String data, Channel channel, Message message) throws IOException{
		try {
			System.out.println("收到延时5s消息了:" + data);
			//int a = 1 / 0;
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		} catch (Exception e) {
			e.printStackTrace();
			 // TODO 最后一个参数 requeue 设置为true 会把消费失败的消息从新添加到队列的尾端，设置为false不会重新回到队列
			//消息记录数据库，后期扫描处理
			channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
		}
	}
	
}
