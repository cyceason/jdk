package com.cyc.five.annotation.target;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Target 使用例子, 此Table可以用于注解类、接口(包括注解类型) 或enum声明
 * 
 * @author Administrator
 *
 */
@Target(ElementType.TYPE)
public @interface Table {
	/**
	 * 数据表名称注解，默认值为类名称
	 * 
	 * @return
	 */
	public String tableName() default "className";
}

/**
 * 此 NoDBColumn仅可用于注解类的成员变量。
 * 
 * @author Administrator
 *
 */
@Target(ElementType.FIELD)
@interface NoDBColumn {

}