package com.qding.api.call.app.qding.v1_3_2.struct.popularize.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * Created by qd on 2015/11/23.
 */
@Validate
public class GetPopularizeStatisticsRequest extends BaseRequest {

    private static final long serialVersionUID = -4773098118450623757L;

    /**
     * 用户ID
     */
    @NotNullValidate
    private String memberId;

    /**
     * 查询类型 ：推广用户数  推广订单数  退单数
     */
    @NotNullValidate
    private Integer type;

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

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String toString() {
        return "GetPopularizeStatisticsRequest [memberId=" + memberId + ",type=" + type + ",pageNo="+pageNo+",pageSize="+pageSize+"]";
    }
}
