package com.zm.entity.order;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Order {
	private String id;
	private String order_no;
	private String order_name;
	private BigDecimal order_money;
	private String user_id;
	private Date create_time;
	private String remark;
}
