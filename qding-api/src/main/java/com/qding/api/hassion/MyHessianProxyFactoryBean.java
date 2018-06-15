package com.qding.api.hassion;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.caucho.hessian.client.HessianProxyFactory;

public class MyHessianProxyFactoryBean implements FactoryBean<Object>, InitializingBean {

	private String serviceUrl;
	
	private Class<?> serviceInterface;
	
    private int readTimeout = 5000;

    private com.caucho.hessian.client.HessianProxyFactory proxyFactory = new HessianProxyFactory();
    
    private Object hassionObject;

	@Override
	public void afterPropertiesSet() throws Exception {
		
		proxyFactory.setChunkedPost(false);
		proxyFactory.setOverloadEnabled(true);
		proxyFactory.setReadTimeout(getReadTimeout());

		hassionObject = proxyFactory.create(getServiceInterface(), serviceUrl);
		
	}
	
	@Override
	public Object getObject() throws Exception {
		
		return hassionObject;
	}

	@Override
	public Class<?> getObjectType() {
		return getServiceInterface();
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public Class<?> getServiceInterface() {
		return serviceInterface;
	}
	
	public void setServiceInterface(Class<?> serviceInterface) {
		this.serviceInterface = serviceInterface;
	}
	
	public int getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}
	
}
