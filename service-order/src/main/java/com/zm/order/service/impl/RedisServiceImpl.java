package com.zm.order.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.zm.order.service.RedisService;

@Component
public class RedisServiceImpl implements RedisService {
	
    @Autowired
    private StringRedisTemplate template;

	@Override
	public boolean set(String key, String value) {
		ValueOperations<String, String> ops = template.opsForValue();
		ops.set(key, value);
		return true;
	}

	@Override
	public boolean mulitSet(Map<String, String> values) {
		this.template.opsForValue().multiSet(values);
		return false;
	}
	
	@Override
	public String get(String key) {
		ValueOperations<String, String> ops = template.opsForValue();
		return ops.get(key);
	}

	@Override
	public boolean expire(String key, long expire) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> boolean setList(String key, List<T> list) {
		// TODO Auto-generated method stub
		ListOperations<String, String> ops = template.opsForList();
		list.forEach(str -> {
			ops.leftPush(key, (String) str);
		});
		return false;
	}

	@Override
	public <T> List<T> getList(String key, Class<T> clz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long lpush(String key, Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long rpush(String key, Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String lpop(String key) {
		// TODO Auto-generated method stub
		return null;
	}


}
