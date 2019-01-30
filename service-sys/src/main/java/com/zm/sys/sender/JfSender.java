package com.zm.sys.sender;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zm.sys.bean.CorrelationData;

import lombok.extern.slf4j.Slf4j;

/**
 * 消息确认机制
 * @author Administrator
 *
 */
@Slf4j
@Service
public class JfSender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback, InitializingBean{

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	/**
	 * 发送消息
	 * @param exchangeName
	 * @param routingKey
	 * @param message
	 */
	public void sendMessage(String exchangeName, String routingKey, Object message){
		CorrelationData correlationData = new CorrelationData("1", message);
		correlationData.setExchange(exchangeName);
		correlationData.setRoutingKey(routingKey);
		correlationData.setRetryCount(1);
		this.convertAndSend(exchangeName, routingKey, message, correlationData);
		//this.delayAndSend(exchangeName, routingKey, message);
	}
	
	/**
	 * 发送消息
	 * @param exchange 交换机
	 * @param routingKey 路由键
	 * @param message 消息
	 * @param jf 消息数据
	 */
	private void convertAndSend(String exchange, String routingKey, final Object message, CorrelationData correlationData){
		try {
			rabbitTemplate.convertAndSend(exchange, routingKey, message, correlationData);
		} catch (Exception e) {
			// TODO: handle exception
			//保存失败消息到数据库
			log.error("消息发送失败");
		}
	}
	
	/**
	 * 延迟消费
	 * @param exchange
	 * @param routingKey
	 * @param msg
	 */
	private void delayAndSend(String exchange, String routingKey, final Object msg){
		rabbitTemplate.convertAndSend(exchange, routingKey, msg, message ->{
			message.getMessageProperties().setExpiration("5000");
			return message;
		});
	}
	
	/**
     * 用于实现消息发送到RabbitMQ交换器后接收ack回调。
     * 如果消息发送确认失败就进行重试。
     * 
	 */
	@Override
	public void confirm(org.springframework.amqp.rabbit.support.CorrelationData correlationData, boolean ack, String cause) {
		// TODO Auto-generated method stub
		 if (!ack && correlationData instanceof CorrelationData) {
			 CorrelationData correlationDataExtends = (CorrelationData) correlationData;
			 if(correlationDataExtends.getRetryCount() > 3){
				 //大于最大重试次数，记入数据库
				 //确认失败
				 System.out.println("积分消息消费失败===============");
			 }else{
				 System.out.println("积分消息进入重试=============");
				 correlationDataExtends.setRetryCount(correlationDataExtends.getRetryCount() + 1);
				 this.convertAndSend(correlationDataExtends.getExchange(), correlationDataExtends.getRoutingKey(), correlationDataExtends.getMessage(), correlationDataExtends);
			 }
		 }else{
			 //消息发送成功
			 System.out.println("积分消息成功消费================");
		 }
	}
	
	/**
	 * 用于实现消息发送到RabbitMQ交换器，但无相应队列与交换器绑定时的回调
	 * 消息从交换器发送到对应队列失败时触发
	 */
	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		log.error("MQ消息发送失败，replyCode:{}, replyText:{}，exchange:{}，routingKey:{}，消息体:{}",
                replyCode, replyText, exchange, routingKey, message.getBody());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		rabbitTemplate.setConfirmCallback(this);
		rabbitTemplate.setReturnCallback(this);
	}

}
