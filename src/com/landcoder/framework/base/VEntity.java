package com.landcoder.framework.base;

/**
 * 数据查询基类
 * @author landcoder
 * @company oschina
 */
public abstract class VEntity {

	private String sortColumns; // 排序字段
	private Integer cpage = 1; // 当前页码 默认为1
	private Integer pageSize = 20; // 每页显示数量 默认为20

	public String getSortColumns() {
		return sortColumns;
	}

	public void setSortColumns(String sortColumns) {
		this.sortColumns = sortColumns;
	}

	public Integer getCpage() {
		return cpage;
	}

	public void setCpage(Integer cpage) {
		this.cpage = cpage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getStartSize() {
		return pageSize * (cpage - 1);
	}
}
