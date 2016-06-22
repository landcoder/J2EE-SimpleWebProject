package com.landcoder.sys.model;

/**
 * 数据字典实体类
 * 
 * @author landcoder
 * @category id 主键
 * @category dictName 字典名
 * @category dictVal 字典值
 * @category pid 字典类型
 */
public class CDictionary implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	private Integer id;
	private String dictName;
	private String dictVal;
	private Integer pid;

	public void setId(Integer value) {
		this.id = value;
	}

	public Integer getId() {
		return this.id;
	}

	public void setDictName(String value) {
		this.dictName = value;
	}

	public String getDictName() {
		return this.dictName;
	}

	public void setDictVal(String value) {
		this.dictVal = value;
	}

	public String getDictVal() {
		return this.dictVal;
	}

	public void setPid(Integer value) {
		this.pid = value;
	}

	public Integer getPid() {
		return this.pid;
	}

}