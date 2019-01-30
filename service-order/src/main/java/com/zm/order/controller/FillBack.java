package com.zm.order.controller;

import java.util.List;

import org.springframework.stereotype.Component;

import com.zm.order.service.OrderService;

@Component
public class FillBack implements OrderService{

	@Override
	public String sendMsg(List<String> list) {
		return "sorry,service error";
	}

}
