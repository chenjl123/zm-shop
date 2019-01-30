package com.zm.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zm.order.bean.Log;
import com.zm.order.dao.LogMapper;
import com.zm.order.service.LogService;

@Service
public class LogServiceImpl implements LogService {
	@Autowired
	private LogMapper mapper;
	
	@Override
	public void add(Log log) {
		// TODO Auto-generated method stub
		mapper.add(log);
	}

}
