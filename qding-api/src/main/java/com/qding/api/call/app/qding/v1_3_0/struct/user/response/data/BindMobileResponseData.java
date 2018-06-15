package com.qding.api.call.app.qding.v1_3_0.struct.user.response.data;


import com.qding.api.call.app.qding.v1_3_0.struct.user.BindMobileInfo;
import com.qding.api.struct.ResponseData;

public class BindMobileResponseData   extends ResponseData{


	private static final long serialVersionUID = -8350486514688086512L;
	
	/**
     * 手机绑定信息
     */
	private BindMobileInfo entity;

	/**
	 * @return the entity
	 */
	public BindMobileInfo getEntity() {
		return entity;
	}

	/**
	 * @param entity the entity to set
	 */
	public void setEntity(BindMobileInfo entity) {
		this.entity = entity;
	}
	

	@Override
    public String toString() {
        return "BindMobileResponseData [entity="+entity+",toString()=" + super.toString() + "]";
    }
}
