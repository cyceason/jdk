package com.cyc.five.introspector;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class BeanInfoUtil {
	/**
	 * 设置单个属性， PropertyDescriptor
	 * 
	 * @param userInfo
	 * @param userName
	 * @throws Exception
	 */
	public void setProperty(UserInfo userInfo, String userName) throws Exception {
		PropertyDescriptor propDesc = new PropertyDescriptor(userName, UserInfo.class);
		Method methodSetUserName = propDesc.getWriteMethod();
		methodSetUserName.invoke(userInfo, "wong");
		System.out.println("set userName:" + userInfo.getUserName());
	}

	/**
	 * 获取单个属性, PropertyDescriptor
	 * 
	 * @param userInfo
	 * @param userName
	 * @throws Exception
	 */
	public void getProperty(UserInfo userInfo, String userName) throws Exception {
		PropertyDescriptor proDescriptor = new PropertyDescriptor(userName, UserInfo.class);
		Method methodGetUserName = proDescriptor.getReadMethod();
		Object objUserName = methodGetUserName.invoke(userInfo);
		System.out.println("get userName:" + objUserName.toString());
	}

	/**
	 * 遍历所有属性, Introspector
	 * 
	 * @param userInfo
	 * @param userName
	 * @throws Exception
	 */
	public void listPropertyByIntrospector(UserInfo userInfo, String userName) throws Exception {
		BeanInfo beanInfo = Introspector.getBeanInfo(UserInfo.class);
		PropertyDescriptor[] proDescrtptors = beanInfo.getPropertyDescriptors();
		if (proDescrtptors != null && proDescrtptors.length > 0) {
			for (PropertyDescriptor propDesc : proDescrtptors) {
				System.out.println(propDesc.getName());
			}
		}
	}

}
