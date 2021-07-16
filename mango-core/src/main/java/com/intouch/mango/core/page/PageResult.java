package com.intouch.mango.core.page;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页请求
 * */
@Data
public class PageResult {
	/* *
	 * @Description :查询当前页码
	 * @Author: gino
	 * @Date: 2021/6/028
	 * @Time: 22:30
	 */
	private  int pageNum;
	/**
	 * 每页数量
	 * */
	private  int pageSize;
	/* *
	 * @Description :记录总数
	 * @Author: gino
	 * @Date: 2021/6/028
	 * @Time: 22:32
	 */
	private long totalSize;
	/* *
	 * @Description:页码总数
	 * @Author: gino
	 * @Date: 2021/6/028
	 * @Time: 22:33
	 */
	private int totalPages;
	/* *
	 * @Description :分页数据
	 * @Author: gino
	 * @Date: 2021/6/028
	 * @Time: 22:34
	 */
	private List<?> content;



}
