package com.base.springmvc.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Will WM. Zhang
 *
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestJsonString {

	/**
	 * 用于绑定的请求参数名字
	 */
	String value() default "";

	/**
	 * 是否必须，默认为true
	 */
	boolean required() default true;

}
