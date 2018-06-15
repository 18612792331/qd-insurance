package com.qding.api.controllers;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.qding.api.annotation.DependencyHttp;
import com.qding.api.annotation.DependencyRpc;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.mock.Mocker;
import com.qding.api.process.pool.ServicePool;
import com.qding.api.process.print.AbstractProtocolPrint;
import com.qding.api.process.print.JsonProtocolPrint;
import com.qding.api.struct.Response;
import com.qding.api.util.ApplicationContextUtil;
import com.qding.api.util.HsqlUtil;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.rule.MaxValueValidate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;
import com.qding.framework.common.util.QDStringUtil;


/**
 * 
 * @author lichao
 *
 */
@Controller
@RequestMapping("/tools")
public class APIToolsController {

	private static Map<String, Map<String, Class<? extends Callable>>> servicePool;

	private Map<String, Integer> versionMethodMap = new HashMap<String, Integer>();
	private HashMap<String,HashMap<String,String>> lastVmethodMap = new HashMap<>();
	private HashMap<String,Set<String>> deprecatedMethodMap = new HashMap<>();


	Map<String, List<ApiMethodInfo>> versionMethodNameMap = new TreeMap<String, List<ApiMethodInfo>>(
			new Comparator<String>() {
				public int compare(String obj1, String obj2) {
					Integer v1 =Integer.parseInt(obj1.toString().replace(".",""));
					Integer v2 =Integer.parseInt(obj2.toString().replace(".",""));
					return v1.compareTo(v2);
				}
			});

	private Map<String, String> map = new HashMap<String, String>() {{
		put("NotNullValidate", "不能为空");
		put("MaxLengthValidate", "最大长度不超过");
		put("MaxValueValidate", "最大值不超过");
		put("MinCollectionsSizeValidate", "");
		put("MinLengthValidate", "最小长度不小于");
		put("MinValueValidate", "最小值不小于");
		put("RangeCollectionsSizeValidate", "");
		put("RangeLengthValidate", "区间长度在");
		put("RangeValueValidate", "区间值在");
		put("RegexpValidate", "");
	}};

	private Map<String, String> aliasMap = new HashMap<String, String>() {{
		put("auction", "夺宝活动");
		put("payment", "支付");
		put("brick", "基础数据");
		put("housekeeper", "管家");
		put("im", "即时通讯");
		put("search-order", "乐购商品");
		put("wallet", "我的钱包");
		put("history-order", "历史订单");
		put("project", "社区");
		put("notify", "通知提醒");
		put("common-upload", "上传");
		put("search-order", "乐购订单");
		put("hotcycle", "邻聚");
		put("points", "通知提醒");
		put("user", "用户信息");
		put("coupon", "千丁券");
		put("popularize", "推广员");
		put("activity", "促销活动");
		put("poster", "APP启动图");
		put("familypayment", "亲情支付");
        put("sysconfig", "系统配置");
	}};

	private int methodCount = 0;

	private void buildDoc(JSONArray doc, String id, String parent, String text, String explain, Object[] keys, Object[] values) {

		JSONObject child = tree(id, parent, text, explain, keys, values, null, null,null);

		doc.add(child);
	}

	private void buildParam(JSONArray tree, String id, String parent, String text, String explain, Object[] keys, Object[] values) {

		JSONObject child = tree(id, parent, text, explain, keys, values, null, null,null);

		tree.add(child);
	}

	private JSONObject tree(String id, String parent, String text, String explain, Object[] keys, Object[] values, String body, String testBody,String mockData) {
		JSONObject child = new JSONObject();
		child.put("id", id);
		child.put("parent", parent);
		child.put("text", text);
		child.put("explain", explain);
		JSONArray doc = new JSONArray();
		for (int i = 0; i < keys.length; i++) {
			JSONObject kv = new JSONObject();
			kv.put("key", keys[i].toString());
			kv.put("value", values[i].toString());
			doc.add(kv);
		}
		child.put("data", doc);
		if (body != null && !body.equals("")) {
			child.put("body", body);
		}
		if (testBody != null && !testBody.equals("")) {
			child.put("testBody", testBody);
		}
		if (mockData != null && !mockData.equals("")) {
		    child.put("mockData", mockData);
		}
		return child;
	}

	private static JSONArray root;

	private JSONArray init() {

		synchronized (APIToolsController.class) {

			if (root == null) {
				root = new JSONArray();
			} else {
				return root;
			}

		}

		servicePool = ServicePool.get();

		buildServices();

		return root;
	}

	public void buildServices() {

		Set<String> aliass = servicePool.keySet();
		buildDoc(root, "API接口说明",
				"#",
				"API接口说明",
				"",
				new String[]{},
				new Object[]{}
		);

   		for (String alias : aliass) {
			Map<String, Class<? extends Callable>> serviceVersionPool = servicePool.get(alias);
			Set<String> versions = serviceVersionPool.keySet();
			String explain = "";
			for (String version : versions){
				try {
					Class<? extends Callable> clazz = serviceVersionPool.get(version);
					ExplainAnnotation classExplainAnnotation = clazz.getAnnotation(ExplainAnnotation.class);
					explain = classExplainAnnotation.explain();
					break;
				} catch (Exception ex) {

				}
			}
			if ("".equals(explain)) {
				if (aliasMap.containsKey(alias)) {
					explain = aliasMap.get(alias);
				}
			}

			buildDoc(root, alias,
					"#",
					alias + " [" + explain + "]",
					explain,
					new String[0],
					new Object[0]
			);

			buildVersions(alias);
		}

		for (String key : lastVmethodMap.keySet()) {
//			System.out.println(key);
			HashMap<String,String> methodC = lastVmethodMap.get(key);
			for (String mkey :methodC.keySet()){
//				System.out.println("     "+mkey+"   <"+methodC.get(mkey)+">");
			}
		}

//		System.out.println("失效方法============");
		for (String key :deprecatedMethodMap.keySet()) {
//			System.out.println(key);
			Set<String> dVSet =  deprecatedMethodMap.get(key);
			StringBuffer sb = new StringBuffer();
			for (String s : dVSet) {
				sb.append(s);
				sb.append(" ");
			}

//			System.out.println("     "+sb.toString());
		}
	}

	private void buildVersions(String alias) {
		Map<String, Class<? extends Callable>> serviceVersionPool = servicePool.get(alias);
		Set<String> versions = serviceVersionPool.keySet();
		for (String version : versions) {
			buildDoc(root, alias + ":" + version,
					alias,
					version,
					"",//explain,
					new String[0],
					new Object[0]
			);

			buildMethods(alias, version);
		}
	}

	private void buildMethods(String alias,
							  String version) {

		Map<String, Class<? extends Callable>> serviceVersionPool = servicePool.get(alias);
		Class<? extends Callable> clazz = serviceVersionPool.get(version);

		Method[] methods = clazz.getDeclaredMethods();

		for (Method method : methods) {

			HTTP http = method.getAnnotation(HTTP.class);
			if (http == null) {
				continue;
			}

			ExplainAnnotation explainAnnotation = method.getAnnotation(ExplainAnnotation.class);
			Deprecated deprecated = method.getAnnotation(Deprecated.class);
			String explain = "";
			if (explainAnnotation != null) {
				explain += explainAnnotation.explain();
			}
			if (deprecated != null) {
				explain += " 已无效";
				continue;
			}
			String id = alias + ":" + version + ":" + http.alias();
			buildDoc(root, id,
					alias + ":" + version,
					http.alias()+(QDStringUtil.isNotEmpty(explain)?" ["+explain+"]":""),
					explain,
					new String[0], new String[0]);
			methodCount++;
			boolean isExit = versionMethodMap.containsKey(version);
			if (isExit) {
				int versionMethodCount = versionMethodMap.get(version);
				versionMethodMap.put(version, ++versionMethodCount);
			} else {
				versionMethodMap.put(version, 1);
			}

			ApiMethodInfo apiMethodInfo = new ApiMethodInfo();
			apiMethodInfo.setExplain(explain);
			apiMethodInfo.setMethodAlias(http.alias());
			apiMethodInfo.setVersion(version);
			apiMethodInfo.setServiceAlias(alias);
			boolean isExitMethod = versionMethodNameMap.containsKey(version);
			if (isExitMethod) {
				List<ApiMethodInfo> methodList  = versionMethodNameMap.get(version);
				methodList.add(apiMethodInfo);
				versionMethodNameMap.put(version,methodList);
			} else {
				List<ApiMethodInfo> methodList = Lists.newArrayList();
				methodList.add(apiMethodInfo);
				versionMethodNameMap.put(version,methodList);
			}
			outMethodsForVersion( alias, http.alias(), version);
		}
	}


	private void outMethodsForVersion(String alias,String methodName,String currVersion){

		boolean isAliasExt = lastVmethodMap.containsKey(alias);
		String currVersionIn = initVersion(currVersion);
		if (Integer.parseInt(currVersionIn)>=300000) return;
		if (isAliasExt) {
			boolean isMethodExt = lastVmethodMap.get(alias).containsKey(methodName);
			if (isMethodExt) {
				String currV = initVersion(currVersion);
				String tmpV = lastVmethodMap.get(alias).get(methodName);
				String mapV = initVersion(tmpV);
				if (Integer.parseInt(currV) > Integer.parseInt(mapV)){
					lastVmethodMap.get(alias).put(methodName,currVersion);
					boolean delExt = deprecatedMethodMap.containsKey(methodName);
					if (delExt) {
						Set deprecatedVSet =  deprecatedMethodMap.get(methodName);
						deprecatedVSet.add(tmpV);
						deprecatedMethodMap.put(methodName,deprecatedVSet);
					} else {
						Set deprecatedVSet = new HashSet();
						deprecatedVSet.add(tmpV);
						deprecatedMethodMap.put(methodName,deprecatedVSet);
					}
				}
			} else {
				lastVmethodMap.get(alias).put(methodName,currVersion);
			}
		} else {
			HashMap<String,String> temMap = new HashMap<>();
			temMap.put(methodName,currVersion);
			lastVmethodMap.put(alias,temMap);
		}
	}

	private String initVersion(String version) {
		version= version.replace(".","");
		version = String.format("%-6s", version).replace(' ', '0');
		return version;
	}

	private JSONObject buildMethod(String serviceAlias, String methodAlias, String version) {
		Map<String, Class<? extends Callable>> serviceVersionPool = servicePool.get(serviceAlias);
		Class<? extends Callable> clazz = serviceVersionPool.get(version);
		Method[] methods = clazz.getDeclaredMethods();

		for (Method method : methods) {
			HTTP http = method.getAnnotation(HTTP.class);
			if (http == null || !http.alias().equals(methodAlias)) {
				continue;
			}
			ExplainAnnotation explainAnnotation = method.getAnnotation(ExplainAnnotation.class);
			Deprecated deprecated = method.getAnnotation(Deprecated.class);
			String methodExplain = "";
			String methodDesc = "";
			String isUse = "可用";
			if (explainAnnotation != null) {
				methodExplain = explainAnnotation.explain();
				methodDesc = explainAnnotation.desc();
			}
			if (deprecated != null) {
				isUse = "已失效";
				continue;
			}

			String id = serviceAlias + ":" + version + ":" + http.alias();

			try {

				JSONArray responseTree = buildResponse(method);

				JSONArray requestTree = buildRequest(method);

				String[] dependencyRpces = bulidDependencyRpc(method);

				String[] bulidDependencyHttp = bulidDependencyHttp(method);

				String requestStr = "body=" + buildRequestParam(method) +"&userToken=";
				requestStr = requestStr.replace("\"appDevice\":null", "\"appDevice\":{\n      \"qdPlatform\":\"IOS\",\n      \"qdDevice\":\"iphone6\",\n      \"qdVersion\":\"" + version + "\"\n }").replace("\"appUser\":null","\"appUser\":{\n   \"projectId\":\"\",\n     \"curMemberId\":\"\"\n  }");
				String testRequestStr = buildTestRequestParam(serviceAlias, version, method.getName());
				String mockData = "";// Mocker.mock(method.getGenericReturnType());
				return tree(id,
						"#",
						http.alias(),
						"",
						new String[]{"方法说明", "方法描述", "请求地址", "可用状态", "服务", "版本号", "调用方法", "支持HTTP方法", "接口认证","采用Token会员ID", "RPC依赖", "HTTP依赖", "TREE:REQUEST", "TREE:RESPONSE"},
						new Object[]{methodExplain, methodDesc, "/api/json/" + serviceAlias + "/" +  http.alias(), isUse, serviceAlias, version, http.alias(), Arrays.toString(http.supportMethod()), http.isRequireAuth()?"是":"否",http.isNeadToken()?"是":"否", Arrays.toString(dependencyRpces), Arrays.toString(bulidDependencyHttp), requestTree.toJSONString(), responseTree.toJSONString()}
						, requestStr, testRequestStr,mockData);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return new JSONObject();
	}

	private String[] bulidDependencyRpc(Method method) {
		String[] dependencyRpces = new String[0];
		DependencyRpc dependencyRpc = method.getAnnotation(DependencyRpc.class);
		if (dependencyRpc != null) {
			Class<?>[] cs = dependencyRpc.clazz();
			String[] ms = dependencyRpc.method();
			if (cs != null && ms != null && cs.length == ms.length) {
				dependencyRpces = new String[cs.length];
				for (int i = 0; i < cs.length; i++) {
					Class<?> c = cs[i];
					String m = ms[i];
					String file = c.getProtectionDomain().getCodeSource().getLocation().getFile();
					dependencyRpces[i] = file.substring(file.lastIndexOf("/") + 1) + "-->" + c.getName() + "-->" + m;
				}
			}
		}
		return dependencyRpces;
	}

	private String[] bulidDependencyHttp(Method method) {
		String[] dependencyHttpes = new String[0];
		DependencyHttp dependencyHttp = method.getAnnotation(DependencyHttp.class);
		if (dependencyHttp != null) {
			return dependencyHttp.url();
		}
		return dependencyHttpes;
	}

	private String buildRequestParam(Method method) {
		AbstractProtocolPrint print = ApplicationContextUtil.getBeansOfType(JsonProtocolPrint.class);

		Class<?>[] parameterTypes = method.getParameterTypes();
		if (parameterTypes.length > 0) {
			Class<?>[] classes = method.getParameterTypes();
			for (Class<?> request : classes) {
				if (BaseRequest.class.isAssignableFrom(request)) {
					try {
						return print.out(request.newInstance());
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	private JSONArray buildRequest(Method method) {
		JSONArray requestTree = new JSONArray();

		Class<?>[] parameterTypes = method.getParameterTypes();
		if (parameterTypes.length > 0) {
			Class<?>[] classes = method.getParameterTypes();
			for (Class<?> request : classes) {
				if (BaseRequest.class.isAssignableFrom(request)) {
					buildParam(requestTree, "request", "#", "request", "", new String[]{}, new String[]{});//explain
					buildJavaBean(requestTree, "request", request);
				}
			}
		}
		return requestTree;
	}

	private JSONArray buildResponse(Method method) {

		JSONArray responseTree = new JSONArray();
		buildParam(responseTree, "response", "#", "response", "", new String[]{}, new String[]{});

		if (Response.class.isAssignableFrom(method.getReturnType())) {

			Type pt = method.getGenericReturnType();

			buildParam(responseTree, "data", "response", "data", "", new String[]{}, new String[]{});
			buildParam(responseTree, "code", "response", "code", "", new String[]{}, new String[]{});

			ParameterizedType type = (ParameterizedType) pt;
			buildJavaBean(responseTree, "data", (Class<?>) type.getActualTypeArguments()[0]);
		}

		return responseTree;
	}

	private void buildJavaBean(JSONArray tree, String parent, Class<?> clazz) {

		if (!isJavaBean(clazz)) {
			return;
		}

		Object target = null;
		try {
			target = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e1) {
			e1.printStackTrace();
		}

		for (Field field : clazz.getDeclaredFields()) {

			if (Modifier.isStatic(field.getModifiers())
					|| !Modifier.isPrivate(field.getModifiers())) {
				continue;
			}

			String id = parent + ":" + field.getName();

			try {

				Class<?> type = field.getType();

				PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);

				Object value = pd.getReadMethod().invoke(target);

				Annotation[] annotations = field.getDeclaredAnnotations();
				String fieldValidateContent = "";
				String explain = "";
				String desc = "";
				for (Annotation annotation : annotations) {
					String annotationSimpleName = annotation.annotationType().getSimpleName();
					boolean annotationFlag = map.containsKey(annotationSimpleName);
					if (annotationFlag && !parent.equals("data")) {
						fieldValidateContent += map.get(annotationSimpleName);
						switch (annotationSimpleName) {

							case "MinValueValidate":
								MinValueValidate minValueValidate = field.getAnnotation(MinValueValidate.class);
								fieldValidateContent += minValueValidate.value();
								break;
							case "MaxValueValidate":
								MaxValueValidate maxValueValidate = field.getAnnotation(MaxValueValidate.class);
								fieldValidateContent += maxValueValidate.value();
								break;
							case "RangeValueValidate":
								RangeValueValidate rangeValueValidate = field.getAnnotation(RangeValueValidate.class);
								fieldValidateContent += rangeValueValidate.min() + " - " + rangeValueValidate.max();
								break;
						}
					} else {
						if ("ExplainAnnotation".equals(annotationSimpleName)) {
							ExplainAnnotation explainAnnotation = field.getAnnotation(ExplainAnnotation.class);
							explain += explainAnnotation.explain();
							desc += explainAnnotation.desc();
						}

					}
				}
				String typeName = "";
				Class fieldClazz = field.getType();
				typeName = fieldClazz.getSimpleName();
				Type typeObj = field.getGenericType();
				if (typeObj instanceof ParameterizedType) {
					ParameterizedType p = (ParameterizedType) typeObj;
					Type[] types = p.getActualTypeArguments();
					if (types.length > 0) {
						typeName += " (";
						for (int i = 0; i < types.length; i++) {
							Class c = (Class) p.getActualTypeArguments()[i];
							typeName += c.getSimpleName();
							if (i + 1 < types.length) {
								typeName += ",";
							} else {
								typeName += ")";
							}
						}
					}
				}
				buildParam(tree, id,
						parent,
						field.getName() + "[" + typeName + "]" + (QDStringUtil.isNotEmpty(explain) ? "[" + explain + "]" : "") + (QDStringUtil.isNotEmpty(desc) ? "[" + desc + "]" : "")+ (QDStringUtil.isNotEmpty(fieldValidateContent) ? ":<font color='red'>" +
								" [" + fieldValidateContent + "]</font>" : ""),
						explain,
						new String[]{field.getName(), "描述"},
						new Object[]{value == null ? "" : value, desc}
				);

				if (Collection.class.isAssignableFrom(type)
						|| Map.class.isAssignableFrom(type)
						|| type.isArray()) {
					Type genericType = field.getGenericType();
					if (genericType instanceof ParameterizedType) {
						ParameterizedType pt = (ParameterizedType) field.getGenericType();
						Class<?> actualType = (Class<?>) pt.getActualTypeArguments()[0];
						buildJavaBean(tree, id, actualType);
					}
				} else {
					buildJavaBean(tree, id, field.getType());
				}
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | IntrospectionException e) {
//				e.printStackTrace();
			}
		}

		if (clazz.getSuperclass() != null && clazz.getSuperclass() != Object.class) {
			buildJavaBean(tree, parent, clazz.getSuperclass());
		}
	}

	private boolean isJavaBean(Class<?> clazz) {

		return clazz.getName().startsWith("com.qding");

	}

	@RequestMapping("console")
	public String console(HttpServletRequest request, HttpServletResponse response) {

		JSONArray root = init();
		request.setAttribute("data", root.toJSONString());
		request.setAttribute("methodCount", methodCount);
		request.setAttribute("vMethod", JSON.toJSONString(versionMethodMap));
		request.setAttribute("lastVmethod",JSON.toJSONString(lastVmethodMap));
		request.setAttribute("vMethodNameList", JSON.toJSONString(versionMethodNameMap));

		return "/api-tools/console/console.jsp";
	}




	@RequestMapping("method")
	public void method(HttpServletResponse response,
					   @RequestParam("serviceAlias") String serviceAlias,
					   @RequestParam("methodAlias") String methodAlias,
					   @RequestParam("version") String version) throws IOException {

		JSONObject doc = buildMethod(serviceAlias, methodAlias, version);

		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(doc.toJSONString());
	}

	@RequestMapping("saveParameter")
	public void saveParameterStr (HttpServletResponse response,
								  @RequestParam("serviceAlias") String serviceAlias,
								  @RequestParam("methodAlias") String methodAlias,
								  @RequestParam("version") String version,
								  @RequestParam("parameterStr") String parameterStr){

		try {
			int updateCount = 0;
			int count = HsqlUtil.queryCount("select id from api_request where serviceAlias='"+serviceAlias+"' and serviceMethod='"+methodAlias+"' and version='"+version+"'");
			if (count ==0){
				 updateCount = HsqlUtil.update("INSERT INTO api_request(serviceAlias,serviceMethod,version,parameterStr)" +
						" VALUES('"+serviceAlias+"','"+methodAlias+"','"+version+"','"+parameterStr+"')");
			} else {
				 updateCount =HsqlUtil.update("UPDATE api_request SET parameterStr='"+parameterStr+"' WHERE serviceAlias='"+serviceAlias+"' and serviceMethod='"+methodAlias+"' and version='"+version+"'");
			}
			JSONObject jsonObject = new JSONObject();
			if (updateCount>0){
				jsonObject.put("code",200);
				jsonObject.put("msg","测试用例已更新");
			} else {
				jsonObject.put("code",400);
				jsonObject.put("msg","测试用例更新失败");
			}
			response.setContentType("text/json;charset=utf-8");
			response.getWriter().write(jsonObject.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping("delParameter")
	public void delParameterStr(HttpServletResponse response,
								@RequestParam("serviceAlias") String serviceAlias,
								@RequestParam("methodAlias") String methodAlias,
								@RequestParam("version") String version){

		try {
			JSONObject jsonObject = new JSONObject();
			int count = HsqlUtil.update("delete from api_request where serviceAlias='"+serviceAlias+"' and serviceMethod='"+methodAlias+"' and version='"+version+"'");
			if (count>0){
				jsonObject.put("code","200");
				jsonObject.put("msg","当前用例清除成功！");
			} else {
				jsonObject.put("code","400");
				jsonObject.put("msg","当前用例清除失败！");
			}
			response.setContentType("text/json;charset=utf-8");
			response.getWriter().write(jsonObject.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String buildTestRequestParam ( String serviceAlias, String version, String serviceMethod ){

		String parameterStr ="";
		try {
//			ResultSet resultSet = HsqlUtil.query("select * from api_request where serviceAlias='"+serviceAlias+"' and serviceMethod='"+serviceMethod+"' and version='"+version+"'");
//			for (; resultSet.next(); ) {
//				parameterStr =  resultSet.getString("parameterStr");
//			}
//			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parameterStr;
	}



	@RequestMapping("selParameter")
	public void selParameterStr(HttpServletResponse response,
								  @RequestParam("serviceAlias") String serviceAlias,
								  @RequestParam("methodAlias") String methodAlias,
								  @RequestParam("version") String version){
		try {
		ResultSet resultSet = HsqlUtil.query("select * from api_request where serviceAlias='"+serviceAlias+"' and serviceMethod='"+methodAlias+"' and version='"+version+"'");
		JSONObject jsonObject = new JSONObject();

		if (resultSet.getRow()>0){
			for (; resultSet.next(); ) {
				String parameterStr =  resultSet.getString("parameterStr");
				Integer id = resultSet.getInt("id");
				jsonObject.put("id",id);
				jsonObject.put("parameterStr",parameterStr);
				jsonObject.put("code","200");
			}
		}else {
			jsonObject.put("code","400");
			jsonObject.put("msg","没有相关测试用例,请手动输入参数提交");
		}
		resultSet.close();
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(jsonObject.toJSONString());
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}