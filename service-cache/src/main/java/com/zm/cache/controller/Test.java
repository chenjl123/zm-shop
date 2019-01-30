package com.zm.cache.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Test {
	@Value("${pro.path}")
	private String proPath;
	
	@GetMapping("/test")
	@ResponseBody
	public String test(){
		return "cache:" + proPath;
	}
}
