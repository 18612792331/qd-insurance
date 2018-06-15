package com.qding.api.process.pool;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.process.GlobalInstance;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;

public class ServicePool{

	// <url功能模块服务别名，<版本号，待回调接口类>>
	private static Map<String, Map<String, Class<? extends Callable>>> servicePool = new HashMap<>();

	private static final Logger logger = Logger.getLogger("service pool");

	/**
	 * 挂载接口服务（服务，版本，接口类）
	 * @param alias url功能模块服务别名
	 * @param version 版本号
	 * @param handler 待回调的接口类
     */
	public static void mount(String alias, String version, Class<? extends Callable> handler) {
		synchronized (servicePool) {
			//如果挂载池中挂在过此模块服务
			if(servicePool.containsKey(alias)) {
				//取出该模块服务中所挂载的不同版本的接口类 map:<版本号，待回调接口类>
				Map<String, Class<? extends Callable>> versionPool = servicePool.get(alias);
				//将当前版本接口类载入池中
				versionPool.put(version, handler);
			}
			
			else {
				//如果没有在挂载池中挂在过此模块服务
				Map<String, Class<? extends Callable>> versionPool = new TreeMap<>(
						GlobalInstance.getVersioncomparestrategy()
				);
				// map:<版本号，待回调接口类>
				versionPool.put(version, handler);
				// map : <url功能模块服务别名，<版本号，待回调接口类>>
				servicePool.put(alias, versionPool);
			}
			
		}
	}

	/**
	 * 挂载接口方法
	 */
	public static synchronized void mountExecutorMethod() {

		//获取挂载池中挂载的服务模块 eg: user,hotcycle ... ...
		Set<String> serviceAliases = servicePool.keySet();

		//遍历取出的所有服务模块 eg:serviceAlias => user
		for(String serviceAlias : serviceAliases) {

			//获取该服务模块下所有版本及对应的接口类Map eg: <1.0 ,com.xx.CallUser>
			Map<String, Class<? extends Callable>> serviceVersion = servicePool.get(serviceAlias);

			//取出该服务模块下的所有版本号
			Set<String> serviceVersions = serviceVersion.keySet();

			//遍历该服务模块下所有版本号
			for(String serviceVerison : serviceVersions) {
				//调用挂载程序，参数eg:	（接口类，模块服务别名，版本号）(com.xx.CallUser.class,user,1.0)
				serviceExecutor.mountExecutor(serviceVersion.get(serviceVerison), serviceAlias, serviceVerison);
			}
		}
	}
	
	public static Class<? extends Callable> get(String alias, String version) throws Exception {
		
		Map<String, Class<? extends Callable>> versionPool = servicePool.get(alias);
		
		if(versionPool == null) {
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), alias + " not found");
		}
		
		return versionPool.get(version);
	}
	
	public static Class<? extends Callable> getLastVersion(String alias) throws ServiceException {
		Map<String, Class<? extends Callable>> versionPool = servicePool.get(alias);
		
		if(versionPool == null) {
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), alias + " not found");
		}
		
		int size = versionPool.values().size();
		if(size > 0) {
			Class<? extends Callable>[] array = (Class<? extends Callable>[]) versionPool.values().toArray(new Class<?>[]{});
			return array[size - 1];
		}
		return null;
	}
	
	public static Class<? extends Callable> getCloseVersion(String alias, String targetVersion) throws ServiceException {
		Map<String, Class<? extends Callable>> versionPool = servicePool.get(alias);
		
		if(versionPool == null) {
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), alias + " not found");
		}
		
		String[] versions = versionPool.keySet().toArray(new String[]{});
		for(int i = 0; i < versions.length; i ++) {
			
			if(GlobalInstance.getVersioncomparestrategy()
					.compare(versions[i], targetVersion) > 0) {
				
				if(i - 1 < 0) {
					//not find
					return getLastVersion(alias);
				}
				
				String closeVersion = versions[i - 1];
				
				logger.info("targetVersion : " + targetVersion + ",closeVersion : " + closeVersion);
				//find
				return versionPool.get(closeVersion);
			}
		}
		//not find
		return getLastVersion(alias);
	}
	
	public static Method getExecutorMethod(Class<? extends Callable> callable, String alias) throws ServiceException {
		
		return serviceExecutor.get(callable, alias);
		
	}
	
	public static Map<String, Map<String, Class<? extends Callable>>> get() {
		
		return Collections.unmodifiableMap(servicePool);
		
	}
	
	private static final ServiceExecutor serviceExecutor = new ServiceExecutor();
	
	public static class ServiceExecutor {
		
		private static Map<Class<? extends Callable>, Map<String, Method>> em = new HashMap<>();

		//很重要，用于记录当前版本挂载的接口类，下面在递归方式挂载超类方法时要用到
		private Class<? extends Callable> currentMountCallable;

		/**
		 * 挂载接口方法程序，参数eg:	（接口类，模块服务别名，版本号）(com.xx.CallUser.class,user,1.0)
		 * @param callable
		 * @param serviceAlias
         * @param version
         */
		public void mountExecutor(Class<? extends Callable> callable, String serviceAlias, String version) {
			
			this.currentMountCallable = callable;
			
			//logger.info("mount callable : " + serviceAlias);
			//logger.info("\t version : " + version);
			
			mountAllVersionExecutor(callable);

		}
		
		/**
		 * 
		 * @param callable 当前要挂载的服务方法
		 */
		private void mountAllVersionExecutor(Class<? extends Callable> callable) {

			//获取当前接口类中的方法
			Method[] methods = callable.getDeclaredMethods();
			
			for(Method method : methods) {
				//排除非公开方法
				if(!Modifier.isPublic(method.getModifiers())) {
					continue;
				}

				HTTP http = method.getAnnotation(HTTP.class);
				
				if(http == null) {
					continue;
				}
				Deprecated deprecated = method.getAnnotation(Deprecated.class);
				if (deprecated != null) {
					continue;//失效方法不再加载
				}
				
				String alias = http.alias(); //获取当前方法的访问别名

				//判断是否挂载过当前接口类部分方法，如果挂在过
				if(em.containsKey(currentMountCallable)) {
					
					Map<String, Method> ems = em.get(currentMountCallable);
					
					if(ems.containsKey(alias)) {
						
						//logger.info("\tOverride executor: "+ http.alias() + " has override from " + callable);
						
						continue;
						
					}
					//如果挂在过，则直接将url请求别名和方法名放入此接口类对应的map中 <接口类，<url请求方法别名，方法>>	<com.xx.CallUser.class,<selUserInfo,getUserInfoMethod>>
					ems.put(alias, method);
						
					//logger.info("\t\t executor: "+ http.alias());
					
				}
				else {
					//如果未挂在过，p 将url请求别名和方法名放入此接口类新创建ma中 <接口类，<url请求方法别名，方法>>	<com.xx.CallUser.class,<selUserInfo,getUserInfoMethod>>
					Map<String, Method> ems = new HashMap<>();
					ems.put(alias, method);
					em.put(currentMountCallable, ems);
					
					//logger.info("\t\t executor: "+ http.alias());
				}
				
			}
			
			/**
			 * 如果有上一个版本 就挂载上一个版本的方法(递归方式，将上一版本中的方法兼容到这一版本中来，注意：currentMountCallable 变量是在方法外定义的)
			 */
			if(callable.getSuperclass() != null && Callable.class.isAssignableFrom(callable.getSuperclass())) {
				
				mountAllVersionExecutor((Class<? extends Callable>) callable.getSuperclass());
			}
		}

		
		public Method get(Class<? extends Callable> callable, String alias) throws ServiceException {
			
			Map<String, Method> ems = em.get(callable);
			
			if(ems == null) {
				
				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), 
						callable + "is not register");
			}
			
			Method executorMethod = ems.get(alias);
			
			if(executorMethod == null) {
				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), 
						"can not find method " + alias + " in " + callable);
			}
			
			return executorMethod;
		}
	}
}
