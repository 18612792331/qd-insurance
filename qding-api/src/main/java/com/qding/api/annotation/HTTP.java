package com.qding.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.qding.api.util.HttpMethod;

/**
 * 只可用在public方法上，表明此方法可被外部接口调用
 * @author lichao
 * 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HTTP {
	
	/**
	 * 调用方法别名
	 * @return
	 */
	String alias();

	
	/**
	 * 支持的http请求方式
	 * @return
	 */
	HttpMethod[] supportMethod() default {HttpMethod.GET, HttpMethod.POST};
	
	/**
	 * 是否需要接口认证
	 * @return
	 */
	boolean isRequireAuth() default false;
	
	/**
	 * 认证过期时 是否需要重新登录
	 * @return
	 */
	boolean isNeedReLoginWhenExpire() default false;

	/**
	 * 是否是登陆入口
	 * @return
     */
	boolean isLogin() default  false;

	/**
	 * 是否需要底层传入社区ID
	 * @return
     */
	boolean isNeadProject() default  false;

	/**
	 * 是否需要传入usertoken
	 * @return
     */
	boolean isNeadToken() default false;

	/**
	 * 是否需要请求签名验证
	 * @return
     */
	boolean isNeedSign() default  false;

	/**
	 * 是否需要最小版本校验
	 * @return
     */
	boolean checkVersion() default  true;
}
