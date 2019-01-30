package com.zm.goods.listener;

import java.io.IOException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class RawDataListener {
	 
	/**
	 * 实时获取kafka数据(生产一条，监听生产topic自动消费一条)
	 * @param record
	 * @throws IOException
	 */
	@KafkaListener(topics = {"AUTH_RES_REFRESH"}, groupId = "order")
    public void listen(ConsumerRecord<String, String> record, Acknowledgment ack) throws IOException { 
		//验证表该消息是否已经处理，没处理就处理业务，消息key加入数据库表，在手动提交偏移量；同一个事物，解决重复消费问题，和消息丢失问题
		String value = record.value();
		System.out.println(value.toString()+"啊哈哈哈哈哈哈");
		ack.acknowledge();
	}
	 
}
