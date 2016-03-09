package com.cdatabank.shuhuibao.core.vo;

import javax.ws.rs.FormParam;


public class PageParam {

	@FormParam("page")
	private int page;//当前所在页数
	@FormParam("rows")
	private int rows;//每页显示条数
	@FormParam("sort")
	private String sort;//排序字段
	@FormParam("order")
	private String order;//排序方式
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	
}
