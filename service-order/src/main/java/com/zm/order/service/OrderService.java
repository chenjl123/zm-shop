package com.zm.order.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zm.order.controller.FillBack;

@FeignClient(value = "service-msg", fallback = FillBack.class)
public interface OrderService {
	@PostMapping(value="/msg/send", headers = {"Accept=application/json","Content-Type=application/x-www-form-urlencoded"})
	String sendMsg(@RequestParam("list") List<String> list);
}
