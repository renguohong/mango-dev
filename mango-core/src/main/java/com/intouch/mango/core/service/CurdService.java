package com.intouch.mango.core.service;

import com.intouch.mango.core.page.PageRequest;
import com.intouch.mango.core.page.PageResult;

import java.util.List;

/**
 * 通用curd接口*/
public interface CurdService <T>{
	/**
	 *@ 保存操作
	* */
	int save(T record);

	/**
	 * 删除操作
	 * */
	int delete(T record);
	/**
	 * 批量删除操作
	 * */
	int delete(List<T> records);

	/**
	 * 根据id查询
	 * */
	T findById(Long id);

	/**
	 * 分页查询
	 * 这里统一封装了分页查询请求和结果，避免直接引入具体框架的分页对象，
	 * 如mybatis或者jpa的分页对象从而避免因为替换orm框架而导致服务层、
	 * 控制层的分页接口也需要变动的情况，替换orm不会影响服务层
	 * 以上的分页查询接口起到了解耦的作用
	 * @param pageRequest   自定义，统一分页查询请求
	 * @return  pageResult     自定义，统一查询分页结果
	 * */
	PageResult findPage(PageRequest pageRequest);

}
