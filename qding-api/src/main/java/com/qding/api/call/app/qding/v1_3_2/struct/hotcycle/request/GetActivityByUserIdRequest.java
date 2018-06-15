package com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * Created by Administrator on 2015/7/27.
 */
@Validate
public class GetActivityByUserIdRequest  extends BaseRequest {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5864785453964737475L;

    @NotNullValidate
	private String userId;

    @MinValueValidate(value="1")
    private int pageNo = 1;

    @RangeValueValidate(max="20", min="1")
    private int pageSize = 10;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public GetActivityByUserIdRequest() {
    }


    @Override
    public String toString() {
        return "GetActivityByUserIdRequest [userId=" + userId + ", pageNo=" + pageNo
                + ", pageSize=" + pageSize + ", toString()="
                + super.toString() + "]";
    }
}
