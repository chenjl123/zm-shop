package com.zm.order.dao;

import org.apache.ibatis.annotations.Mapper;

import com.zm.order.bean.Log;

@Mapper
public interface LogMapper {
	/**
	 * 添加日志
	 * @param log
	 */
	void add(Log log);
}
