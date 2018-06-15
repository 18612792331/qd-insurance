package com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by Administrator on 2015/7/20.
 */
@Validate
public class GetChartletRequest extends BaseRequest {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5742283957461646444L;
	/**
     * 社区ID
     */
    private String communityId;


    public GetChartletRequest (){

    }


	public String getCommunityId() {
		return communityId;
	}


	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}

   


}
