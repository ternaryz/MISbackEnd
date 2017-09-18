package com.tongyuan.pageModel;

/**
 * easyui的datagrid向后台传递参数使用的model
 * 
 * @author 孙宇
 * 
 */
public class EasyuiDataGrid implements java.io.Serializable {

	private int page = 1;// 当前页为1
	private int rows = 10;// 每页显示记录10
	private String sort = null;// 排序字段为null
	private String order = "asc";// 按升序排序

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

}
