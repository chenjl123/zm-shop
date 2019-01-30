package com.zm.order.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zm.order.aop.annotation.BusLog;
import com.zm.order.service.OrderService;
import com.zm.order.service.RedisService;

@Controller
@RequestMapping("/order")
public class OrderAddController {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private RedisService redisService;  
	
	@RequestMapping("/add")
	@ResponseBody
	@BusLog(op_from="order", op_type="inner")
	public String addOrder(){
		System.out.println("request two name");
		List<String> list = Arrays.asList(
			new String("test1"),
			new String("test2")
		);
		//String str = orderService.sendMsg(list);
		String str = "插入日志成功";
		System.out.println(str);
		return str;
	}
	
	@RequestMapping("/orderInfo")
	@ResponseBody
	public String orderInfo(){
		redisService.set("name", "cjl");
		System.out.println("controller 进入");
		return "get order success" + redisService.get("name");
	}
	
}
