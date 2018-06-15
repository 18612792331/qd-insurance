package com.qding.api.util;

import java.io.*;
import java.util.List;
import java.util.Properties;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.qding.api.call.app.qding.v1_3_0.struct.wallet.MoneyDetail;
import com.qding.api.call.app.qding.v2_0_0.struct.housekeeper.NoticeTypeInfoBean;
import com.qding.api.call.app.qding.v2_0_0.struct.project.LgConfigBean;
import com.qding.api.constant.Constant;

public class APIPropertiesClient {
	
	private  static Properties properties = new Properties();

	private String productPath;
	
	@SuppressWarnings("unused")
	private void springInit (){
		
		try {
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(productPath);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			properties.load(bufferedReader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	public static Properties  getProperties (){

		return properties;
	}


	public static String  getPopularizeUrl(Integer status,String code) {

		String url="";
		//如果是审核通过
		if (Constant.POPULARIZE_PASS_AUDIT==status) {
			 url= properties.getProperty("popularize.url");
			 url=url+"/"+code;
		} else {
			 url= properties.getProperty("popularize.frozen.url");
		}
		return url;
	}

	public static String getUrl(String urlType){
		String key=urlType+".base";
		String url= properties.getProperty(key);
		return url;
	}

	public static String getUrlContent(String key){
		String url= properties.getProperty(key);
		return url;
	}

	public static List<NoticeTypeInfoBean> selNoticeTypeInfoBean(){

		String text =  properties.getProperty("notict_type_info");
		JSONArray noticeJsonArray = JSONArray.parseArray(text);
		List<NoticeTypeInfoBean> list = Lists.newArrayList();
		for (int i=0;i<noticeJsonArray.size();i++){
			NoticeTypeInfoBean noticeBean = new NoticeTypeInfoBean();
			JSONObject noticeTypeObj = noticeJsonArray.getJSONObject(i);
			noticeBean.setNoticeColor(noticeTypeObj.getString("noticeColor"));
			noticeBean.setNoticeName(noticeTypeObj.getString("notictName"));
			noticeBean.setNoticeType(noticeTypeObj.getInteger("noticeType"));
			list.add(noticeBean);
		}
		return list;
	}

	public static List<LgConfigBean>  fittingHomLgConfigBean (){

		String text =  properties.getProperty("home_le_config_info");
		JSONArray lgConfigJsonArray = JSONArray.parseArray(text);
		List<LgConfigBean> lgConfigBeanList = Lists.newArrayList();

		for (int i=0;i<lgConfigJsonArray.size();i++){

			JSONObject lgConfigObj = lgConfigJsonArray.getJSONObject(i);
			LgConfigBean lgConfigBean = new LgConfigBean();
			lgConfigBean.setTitle(lgConfigObj.getString("title"));
			lgConfigBean.setImgUrl(lgConfigObj.getString("imgUrl"));
			lgConfigBean.setRevealCategoryId(lgConfigObj.getString("revealCategoryId"));
			lgConfigBeanList.add(lgConfigBean);
		}

		return lgConfigBeanList;

	}
	
	
	/**
	 * 获取订单详情的访问路径(只针对html5跳转的方式)
	 * @param obj
	 * @return
	 */
	public  static MoneyDetail getProductOrderUrl (MoneyDetail obj) {
		
//		String valueString= properties.getProperty(obj.getProductNo()+"_ORDER_URL");
		
//		if ( QDStringUtil.isNull(valueString) || QDStringUtil.isEmpty(valueString))  return obj;
			
		StringBuffer url = new StringBuffer("http://qdtest/"); //valueString
			
		String orderCode = obj.getOrderCode();
		
		switch (obj.getProductNo()) {
		
			case "XC":
				
				obj.setOrderUrl(url.append(orderCode).toString());
				obj.setSkipStatus(Constant.SKIP_NO);
				
				break;

			case "BJ":
				obj.setOrderUrl(url.append(orderCode).toString());
				obj.setSkipStatus(Constant.SKIP_NO);

			default:
				break;
		}
		
		return obj;
		
	}

	public static String getValue(String key){

		String value= properties.getProperty(key);

		return value;
	}


	public static String getSkipUrl (String urlType){

		String url= properties.getProperty("skip_url_"+urlType);

		return url;
	}
	
	public static String getLableShareUrl (){

		String url= properties.getProperty("lable.shareurl");

		return url;
	}
	
	public static String getTopicShareUrl (){

		String url= properties.getProperty("topic.shareurl");

		return url;
	}
	


	public String getProductPath() {
		return productPath;
	}

	public void setProductPath(String productPath) {
		this.productPath = productPath;
	}

	public static String convertUnicode(String ori){
		char aChar;
		int len = ori.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = ori.charAt(x++);
			if (aChar == '\\') {
				aChar = ori.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = ori.charAt(x++);
						switch (aChar) {
							case '0':
							case '1':
							case '2':
							case '3':
							case '4':
							case '5':
							case '6':
							case '7':
							case '8':
							case '9':
								value = (value << 4) + aChar - '0';
								break;
							case 'a':
							case 'b':
							case 'c':
							case 'd':
							case 'e':
							case 'f':
								value = (value << 4) + 10 + aChar - 'a';
								break;
							case 'A':
							case 'B':
							case 'C':
							case 'D':
							case 'E':
							case 'F':
								value = (value << 4) + 10 + aChar - 'A';
								break;
							default:
								throw new IllegalArgumentException(
										"Malformed   \\uxxxx   encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			} else
				outBuffer.append(aChar);

		}
		return outBuffer.toString();
	}


}
