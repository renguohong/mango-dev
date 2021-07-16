package com.intouch.mango.core.page;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/* *
  @ClassName :PageRequest
 * @Description :分页查询返回结果
 * @Author: gino
 * @Date: 2021/6/028
 * @Time: 22:29
 */
@Data
public class PageRequest {
	/**
	 * 当前页码
	 * */
	private int pageNum = 1;

	/**
	 * 每页数量
	 * */
	private int pageSize = 10;
	/**
	 * 查询参数，每页数量
	 * */
	private Map<String,Object> params= new HashMap<>();

	public Object getParam(String key) {
		return getParams().get(key);
	}

}
