package com.zm.goods.listener;

import java.io.IOException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class OrderDataListener {
	 
	/**
	 * 实时获取kafka数据(生产一条，监听生产topic自动消费一条)
	 * @param record
	 * @throws IOException
	 */
	@KafkaListener(topics = {"goods_minus"})
    public void listen(ConsumerRecord<String, String> record, Acknowledgment ack) throws IOException { 
		String value = record.value();
		System.out.println(value.toString()+"库存数量减少一");
		ack.acknowledge();
	}
	 
}
