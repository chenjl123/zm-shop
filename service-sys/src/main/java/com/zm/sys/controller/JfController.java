package com.zm.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zm.sys.sender.JfSender;

@RestController
public class JfController {
	@Autowired
	private JfSender jfSender;
	
	@ResponseBody
	@GetMapping("jfAdd")
	public String jfAdd(){
		jfSender.sendMessage("deal.fexchange", "jf.router", "注册成功，积分加100");
		return "100 add";
	}
}
