package com.qding.api.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qding.api.constant.Constant;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.http.QDHttpClientService;
import com.qding.framework.common.util.QDStringUtil;
import org.apache.log4j.Logger;

import java.net.URLEncoder;

/**
 * 长链接转短连接
 * Created by qd on 2017/3/25.
 */
public final class CreateShortUrlUtil {

    private CreateShortUrlUtil(){}

    private static Logger logger = Logger.getLogger(CreateShortUrlUtil.class);

    public static String ServiceUrlConvertShortUrl(String url, Long expressTime) {
        logger.info("ServiceUrlConvertShortUrl long url==="+url);
        String shortUrl = "400";
        try {
            String convertUrl  = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.URL_CONVERT_DICTNAME.toString(), Constant.URL_CONVERT_DICTNAME.toString());
            logger.info("ServiceUrlConvertShortUrl convertUrl==="+convertUrl);
            url = URLEncoder.encode(url, "utf-8");
            String reqUrl = "";
            if(expressTime!=null && expressTime.longValue()>0){
                reqUrl = String.format((convertUrl + "/new?u=%s&t=%s"),url,expressTime);
            }else{
                reqUrl = String.format((convertUrl + "/new?u=%s"),url);
            }
            logger.info("ServiceUrlConvertShortUrl reqUrl==="+reqUrl);
            String responseStr = QDHttpClientService.sendGetRequest(reqUrl, "utf-8");
            logger.info("responseStr=" + responseStr);
            if (QDStringUtil.isNotNull(responseStr)) {
                JSONObject jsonObject = JSON.parseObject(responseStr.toString());
                logger.info("短连接参数输入："+responseStr);
                if (jsonObject.containsKey("hash")) {
                    shortUrl = jsonObject.get("hash").toString();
                    logger.info("短连接产出："+convertUrl+"/"+shortUrl);
                    return  convertUrl+"/"+shortUrl;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);

        }
        return shortUrl;
    }
}
