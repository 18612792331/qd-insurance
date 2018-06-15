package com.qding.api.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ning.http.util.Base64;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.storage.qiniu.ImgServiceForAPPInPrivate;
import com.qding.framework.common.storage.qiniu.ImgServiceInPublic;
import com.qding.framework.common.util.QDStringUtil;
import com.qiniu.util.UrlSafeBase64;

import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import com.qiniu.common.QiniuException;

public class ImageUtil {

	@Autowired
	private ImgServiceForAPPInPrivate imgServiceForAPP;

	@Autowired
	private ImgServiceInPublic imgService;

	private String resizeImageByQiniu(String key, int width, int height) {

		try {
			//七牛 图片服务，key就是 url
			return imgServiceForAPP.GetDownloadThumbnailUrl(key, 2, width, height);

		} catch (QiniuException e) {
			e.printStackTrace();
		}

		return key;
	}


	private String resizeImageByQiniuPublic(String imgUrl, int width, int height){

		try {
			//七牛 图片服务，key就是 url
			return imgService.GetDownloadThumbnailUrl(imgUrl, 2, width, height);

		} catch (QiniuException e) {
			e.printStackTrace();
		}

		return imgUrl;
	}


	public String get(String key, int width, int height) {

		if (QDStringUtil.isEmpty(key)){
			return "";
		}
		//七牛 图片，根据key反解出七牛图片url
		if(isPrivateQiniu(key)) {
			return resizeImageByQiniu(key, width, height);
		}else if(isPublicQiniu(key)){
			return resizeImageByQiniuPublic(key, width, height);
		} else if (isInvalid(key)){
			return "";
		}
		else {
			return key;
		}

	}

	public String get(String key) {

		if (QDStringUtil.isEmpty(key)){
			return "";
		}

		try {
			if(isPrivateQiniu(key)) {
				//七牛 图片服务
				return imgServiceForAPP.GetDownloadImgUrl(key);
			}
			else if(isPublicQiniu(key)) {
				return key;
			} else if (isInvalid(key)){ //如果是无效图片
				return "";
			} else {
				return key;
			}

		} catch (QiniuException e) {
			e.printStackTrace();
		}
		
		return key;
	}

	private boolean isInvalid (String imageUrl) {

		return imageUrl.startsWith("?imageView");
	}
	
	private boolean isPrivateQiniu(String imageUrl) {
		
		if(imageUrl == null) {
			return false;
		}

		return imageUrl.startsWith("qiniu:qding:");
	}

	private boolean isPublicQiniu(String imageUrl) {

		if(imageUrl == null) {
			return false;
		}

		return imageUrl.startsWith("https://img1.qdingnet.com");

	}
	
	public static String LgImg(String url,Integer type) {
		String suburl=url;
		String watermark=null;
		if(url.indexOf("?")!=-1){
			int i=url.indexOf("?");
			suburl=url.substring(0,i);
			watermark="%7C"+url.substring(i+1);
		}
		if(QDStringUtil.isNotEmpty(suburl)){
			if (1==type) {
				suburl+= "?imageView2/1/w/344/h/344";
			} else if(2==type){
				suburl+= "?imageView2/1/w/750/h/700";
			} else if (3 == type) {
				suburl+= "?imageView2/1/w/710/h/355";
			} else if (4 == type) {
				//3.3 新增 小号规格缩略图
				suburl+= "?imageView2/1/w/200/h/200";
			}
		}
		if(StringUtils.isNotBlank(watermark)){
			suburl+=watermark;
		}
		return suburl;
	}
	
	public static String LgImg_bak(String url,Integer type) {
		if(QDStringUtil.isNotEmpty(url)){
			if (1==type) {
				url+= "?imageView2/1/w/344/h/344";
			} else if(2==type){
				url+= "?imageView2/1/w/750/h/700";
			} else if (3 == type) {
				url+= "?imageView2/1/w/710/h/355";
			}
		}
		return url;
	}


	/**
	 * 获取水印图片
	 * @param sourceImgUrl
	 * @param waterMarkConfigBean
     * @return
     */
	public static String getImgForWaterMark (String sourceImgUrl,WaterMarkConfigBean waterMarkConfigBean){

		if (QDStringUtil.isNotNull(waterMarkConfigBean) && QDStringUtil.isNotNull(waterMarkConfigBean.getWaterMarkImgUrl())) {

			String waterMarkImgUrl = waterMarkConfigBean.getWaterMarkImgUrl();

			try {
				StringBuffer imgUrl = new StringBuffer(sourceImgUrl);

				if (waterMarkConfigBean.getIsPipeline()) {
					imgUrl.append("%7Cwatermark/1/image/");
				} else {
					imgUrl.append("?watermark/1/image/");
				}

				if (QDStringUtil.isNotNull(waterMarkConfigBean.getScaling()) && waterMarkConfigBean.getScaling()>0) {
					waterMarkImgUrl = waterMarkImgUrl+"?imageMogr2/thumbnail/!"+waterMarkConfigBean.getScaling()+"p";
				}

				imgUrl.append(UrlSafeBase64.encodeToString(waterMarkImgUrl));

				if (QDStringUtil.isNotNull(waterMarkConfigBean.getDissolve())) {
					imgUrl.append("/dissolve/"+waterMarkConfigBean.getDissolve());
				}
				if (QDStringUtil.isNotEmpty(waterMarkConfigBean.getGravity())){
					imgUrl.append("/gravity/"+waterMarkConfigBean.getGravity());
				}

				if (QDStringUtil.isNotNull(waterMarkConfigBean.getDx()) && QDStringUtil.isNotNull(waterMarkConfigBean.getDy())) {
					imgUrl.append("/dx/"+waterMarkConfigBean.getDx()+"/dy/"+waterMarkConfigBean.getDy());
				}

				if (QDStringUtil.isNotNull(waterMarkConfigBean.getWs()) && (waterMarkConfigBean.getWs()>0 || waterMarkConfigBean.getWs()<1)){
					imgUrl.append("/ws/"+waterMarkConfigBean.getWs());
				}

				return  imgUrl.toString();

			}catch (Exception ex) {
				ex.printStackTrace();
				return sourceImgUrl;
			}

		} else {
			return sourceImgUrl;
		}

	}

	public  String httpsUrl(String imgUrl) {

		if (QDStringUtil.isNotEmpty(imgUrl)) {
			if(imgUrl.startsWith("https:")) return imgUrl;
			if(imgUrl.startsWith("http:")) return imgUrl.replace("http:","https:");
			if (imgUrl.startsWith("qiniu:qding:")){
				String tmpUrl = resizeImageByQiniu(imgUrl, 150, 150);
				if(tmpUrl.startsWith("http:")){
					imgUrl = tmpUrl.replace("http:","https:");
				} else{
					imgUrl="";
				}
				return  imgUrl;
			}
			return "https://"+imgUrl;
		}
		return "";
	}

}
