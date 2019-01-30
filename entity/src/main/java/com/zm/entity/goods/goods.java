package com.zm.entity.goods;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class goods {
	private String id;
	private String bar;
	private String name;
	private Integer kc;
	private BigDecimal price;
}
