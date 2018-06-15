package com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * Created by Administrator on 2015/7/23.
 */
@Validate
public class GetActivityByCommunityIdRequest extends BaseRequest {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6347300349038400426L;

	/**
     * 社区ID
     */
    @NotNullValidate
    private String communityId;

    /**
     * 查询页码
     */
    @MinValueValidate(value="1")
    private Integer pageNo = 1;

    /**
     * 查询每页显示数
     */
    @RangeValueValidate(max="20", min="1")
    private Integer pageSize = 10;

    public GetActivityByCommunityIdRequest(){

    }

    public GetActivityByCommunityIdRequest(String communityId, Integer pageNo, Integer pageSize) {
        this.communityId = communityId;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    @Override
    public String toString() {
        return "GetActivityByCommunityIdRequest [communityId=" + communityId + ", pageNo=" + pageNo
                + ", pageSize=" + pageSize + ", toString()="
                + super.toString() + "]";
    }
}
