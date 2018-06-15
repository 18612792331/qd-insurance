package com.qding.api.call.app.qding.v2_5_0.struct.housekeeper.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

@Validate
public class GetFeeOrderListRequest extends BaseRequest {

    private static final long serialVersionUID = -2024928322741576103L;

    @RangeValueValidate(max = "20", min = "1")
    @ExplainAnnotation(explain = "页大小")
    private Integer pageSize = 10;

    @MinValueValidate(value = "1")
    @ExplainAnnotation(explain = "当前页码")
    private Integer pageNo = 1;

    @NotNullValidate
    @ExplainAnnotation(explain = "房间ID")
    private String roomId;

    //这里设置成非必填字段，保证支付宝服务窗那块不登陆也能查看房屋未缴物业费
    @ExplainAnnotation(explain = "memberId")
    private String memberId;

    public GetFeeOrderListRequest() {

    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "GetFeeOrderListRequest{" +
                "pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                ", roomId='" + roomId + '\'' +
                ", memberId='" + memberId + '\'' +
                '}';
    }
}
