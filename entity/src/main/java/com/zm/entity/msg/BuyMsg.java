package com.zm.entity.msg;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BuyMsg {
	private String id;
	private String title;
	private String content;
	private String user_id;
}
