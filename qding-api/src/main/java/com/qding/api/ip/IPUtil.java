package com.qding.api.ip;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qding.framework.common.http.QDHttpClientService;
import com.qding.framework.common.util.QDStringUtil;

public class IPUtil {

	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-real-ip");
		String localIP = "127.0.0.1";
		
		if ((ip == null) || (ip.length() == 0)
				|| (ip.equalsIgnoreCase(localIP))
				|| "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("x-forwarded-for");
		}
		
		if ((ip == null) || (ip.length() == 0)
				|| (ip.equalsIgnoreCase(localIP))
				|| "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}

		if ((ip == null) || (ip.length() == 0)
				|| (ip.equalsIgnoreCase(localIP))
				|| "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if ((ip == null) || (ip.length() == 0)
				|| (ip.equalsIgnoreCase(localIP))
				|| "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}

	/**
	 * 通过IP获取城市信息
	 * @param ip
	 * @return
     */
	public static TaoBaoCity getIpCity(String ip){

		return  getIpCityByGd(ip);
	}


	private static TaoBaoCity getIpCityByTb(String ip) {
		
		String s = QDHttpClientService.sendGetRequest("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip, "utf-8");
		try {
			if (QDStringUtil.isNotEmpty(s) && s.indexOf("\"code\":0")!=-1) {

				JSONObject json = JSON.parseObject(s);

				JSONObject data = json.getJSONObject("data");

				TaoBaoCity city = new TaoBaoCity(data.getString("country"), data.getString("region"), data.getString("city"));

				return city;
			}
		}catch (Exception ex) {
			return new TaoBaoCity("未知", "未知", "未知");
		}
		return new TaoBaoCity("未知", "未知", "未知");
	}

	private static TaoBaoCity getIpCityByGd(String ip) {

		String s = QDHttpClientService.sendGetRequest("http://restapi.amap.com/v3/ip?ip=" + ip+"&key=040cd62656f7d70b1f4bafeb05c77a27", "utf-8");
		try {
			if (QDStringUtil.isNotEmpty(s) && s.indexOf("\"status\":0")==-1) {

				JSONObject json = JSON.parseObject(s);

				TaoBaoCity city = new TaoBaoCity("中国", json.getString("province"), json.getString("city"));

				return city;
			}
		}catch (Exception ex) {
			return new TaoBaoCity("未知", "未知", "未知");
		}
		return new TaoBaoCity("未知", "未知", "未知");
	}


	public static   void  main(String args[]){

		System.out.println(JSON.toJSONString(IPUtil.getIpCityByGd("61.135.169.125")));
	}

}

