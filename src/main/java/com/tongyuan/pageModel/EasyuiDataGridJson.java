package com.tongyuan.pageModel;

import java.util.List;

/**
 * EasyuiDataGridJson
 * @author 孙宇
 *
 */
public class EasyuiDataGridJson implements java.io.Serializable {

	private Long total;
	private List rows;
	private List footer;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public List getFooter() {
		return footer;
	}

	public void setFooter(List footer) {
		this.footer = footer;
	}

}
