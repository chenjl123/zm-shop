package com.zm.msg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/msg")
public class MsgTest {
	
	@Value("${pro.path}")
	private String proPath;
	
	@PostMapping("/send")
	public String send(@RequestParam("list") List<String> list){
		list.forEach(str -> {
			System.out.println(str);
		});
		return "success";
	}
	
	@GetMapping("/test")
	@ResponseBody
	public String test(){
		return "msg:" + proPath;
	}
}
