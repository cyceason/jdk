package com.cyc.five.annotation.retention;

import java.lang.annotation.*;

/**
 * Column注解的的RetentionPolicy的属性值是RUTIME,这样注解处理器可以通过反射，获取到该注解的属性值，从而去做一些运行时的逻辑处理
 * 
 * @author Administrator
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	public String name() default "fieldName";

	public String setFuncName() default "setField";

	public String getFuncName() default "getField";

	public boolean defaultDBValue() default false;
}