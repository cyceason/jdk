package com.cyc.six.jaxb.list;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlType
public class Country {

	@XmlElement(name = "country_name")
	private String name;

	@XmlElementWrapper(name = "provinces")
	@XmlElement(name = "province")
	private List<Province> provinceList;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the provinceList
	 */
	public List<Province> getProvinceList() {
		return provinceList;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param provinceList
	 *            the provinceList to set
	 */
	public void setProvinceList(List<Province> provinceList) {
		this.provinceList = provinceList;
	}

	@Override
	public String toString() {
		return "Country [name=" + name + ", provinceList=" + provinceList + "]";
	}

}