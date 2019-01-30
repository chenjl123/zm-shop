package com.zm.sys.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 发送消息的相关数据
 * @author Administrator
 *
 */
@Setter
@Getter
@ToString
public class CorrelationData extends  org.springframework.amqp.rabbit.support.CorrelationData{
	/**
	 * 消息体
	 */
	private volatile Object message;

	/**
	 * 交换机名称
	 */
	private String exchange;

	/**
	 * 路由key
	 */
	private String routingKey;

	/**
	 * 重试次数
	 */
	private int retryCount = 0;

	public CorrelationData() {
		super();
	}

	public CorrelationData(String id) {
		super(id);
	}

	public CorrelationData(String id, Object data) {
		this(id);
		this.message = data;
	}
}
