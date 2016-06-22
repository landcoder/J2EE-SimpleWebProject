package com.landcoder.common.web.ip;

/**
 * 新浪ip地址实体
 * @author landcoder
 * @company oschina
 */
public class SinaIPModel {
	private Integer ret;
	private Integer start;
	private Integer end;
	private String country;
	private String province;
	private String city;
	private String dictrict;
	private String isp;
	private String type;
	private String desc;

	public Integer getRet() {
		return ret;
	}

	public void setRet(Integer ret) {
		this.ret = ret;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDictrict() {
		return dictrict;
	}

	public void setDictrict(String dictrict) {
		this.dictrict = dictrict;
	}

	public String getIsp() {
		return isp;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
