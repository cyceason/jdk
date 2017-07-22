package com.cyc.six.jaxb.list;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class Province {

	@XmlElement(name = "province_name")
	private String name;

	@XmlElement(name = "prov_city")
	private String provCity;

	public String getProvCity() {
		return provCity;
	}

	public void setProvCity(String provCity) {
		this.provCity = provCity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Province [name=" + name + ", provCity=" + provCity + "]";
	}

}