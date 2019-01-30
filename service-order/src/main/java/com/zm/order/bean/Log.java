package com.zm.order.bean;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Log {
	private String id;
	private String op_from;
	private String op_type;
	private Date op_time;
	private String op_user;
}
