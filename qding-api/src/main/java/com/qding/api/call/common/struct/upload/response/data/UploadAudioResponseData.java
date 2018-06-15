package com.qding.api.call.common.struct.upload.response.data;

import com.qding.api.struct.ResponseData;

public class UploadAudioResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7810052210743277992L;

	private String[] list;
	
	public UploadAudioResponseData() {

	}

	public UploadAudioResponseData(String[] list) {
		super();
		this.list = list;
	}

	
	public String[] getList() {
		return list;
	}
	
	public void setList(String[] list) {
		this.list = list;
	}
}
