package com.qding.api.call.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.qding.framework.common.storage.qiniu.ImgServiceInPublic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.common.struct.upload.response.data.UploadAudioResponseData;
import com.qding.api.call.common.struct.upload.response.data.UploadImageResponseData;
import com.qding.api.struct.Response;
import com.qding.api.util.HttpMethod;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.storage.qiniu.ImgServiceInPublic;

public class CallCommonUpload extends Callable{

	private final static List<String> supportImageType = new ArrayList<String>();  
	  
	static {
		
		supportImageType.add(".jpg");  
		supportImageType.add(".jpeg");  
		supportImageType.add(".bmp");  
		supportImageType.add(".gif");  
		supportImageType.add(".png"); 
        
	}
	
	@Autowired
	private ImgServiceInPublic imgService;
	
	@HTTP(alias="uploadImage",checkVersion = false, supportMethod={HttpMethod.POST})
	public Response<UploadImageResponseData> uploadImage(HttpServletRequest request) {

		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
     
			List<MultipartFile> images = multipartRequest.getFiles("images");  
			
			List<String> imageUrls = new ArrayList<>();
			
			for(MultipartFile image : images) {
				
				if(image == null || StringUtils.isEmpty(image.getOriginalFilename())) {
					
					throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "请选择上传图片");
	
				}
				
				String fileName = image.getOriginalFilename();  
				String ext = fileName.substring(fileName.lastIndexOf(".") ,fileName.length()).toLowerCase();  
	
				if(image.getContentType().indexOf("image") == -1) {
	
					throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "不是有效的图片");
	
				}
				if(!supportImageType.contains(ext)) {
					
					throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "不支持的图片格式");
				}
					
				File tempFile = File.createTempFile("upload-uploadImage", ext);

				try {

					image.transferTo(tempFile);

					String key = String.format("qiniu:qding:api:%s%s", UUID.randomUUID(), ext);

					String imgUrl = imgService.UploadImg(tempFile.getAbsolutePath(), key,true);

					imageUrls.add(imgUrl);

				}catch (Exception ex) {
					ex.printStackTrace();
				}finally {
					tempFile.delete();
				}
			}
			
			Response<UploadImageResponseData> response = new Response<UploadImageResponseData>();
			
			response.setData(new UploadImageResponseData(imageUrls.toArray(new String[]{})));
			
			return response;
		} catch (Exception e) {
			
			return handleException(UploadImageResponseData.class, e);
		}  
        
	}
	
	
	@HTTP(alias="uploadAudio",checkVersion = false, supportMethod={HttpMethod.POST})
	public Response<UploadAudioResponseData> uploadAudio(HttpServletRequest request) {

		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
     
			List<MultipartFile> audios = multipartRequest.getFiles("audios");  
			
			List<String> audiosUrl = new ArrayList<>();
			
			for(MultipartFile audio : audios) {
				
				if(audio == null || StringUtils.isEmpty(audio.getOriginalFilename())) {
					
					throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "请选择上传图片");
	
				}
				
				String fileName = audio.getOriginalFilename();  
				String ext = fileName.substring(fileName.lastIndexOf(".") ,fileName.length()).toLowerCase();  
	
				if(audio.getContentType().indexOf("audio") == -1) {
	
					throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "不是有效的音频文件");
	
				}

				File tempFile = File.createTempFile("upload-uploadAudio", ext);
				try{
					audio.transferTo(tempFile);

					String key = String.format("qiniu:qding:api:%s%s", UUID.randomUUID(), ext);

					String url = imgService.UploadImg(tempFile.getAbsolutePath(), key,true);

					audiosUrl.add(url);
				}catch (Exception ex) {
					ex.printStackTrace();
				}finally {
					tempFile.delete();
				}
			}
			
			Response<UploadAudioResponseData> response = new Response<UploadAudioResponseData>();
			
			response.setData(new UploadAudioResponseData(audiosUrl.toArray(new String[]{})));
			
			return response;
		} catch (Exception e) {
			
			return handleException(UploadAudioResponseData.class, e);
		}  
        
	}
}
