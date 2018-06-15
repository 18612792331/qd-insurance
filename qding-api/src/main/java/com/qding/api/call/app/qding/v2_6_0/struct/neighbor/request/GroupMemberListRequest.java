package com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * Created by qd on 2015/10/27.
 */

@Validate
public class GroupMemberListRequest extends BaseRequest {

    private static final long serialVersionUID = 1932072619466809090L;

    @NotNullValidate
    @ExplainAnnotation (explain = "会员ID")
    private String memberId;

    @ExplainAnnotation (explain = "这里为主题ID")
    @NotNullValidate
    private String gcRoomId;

    @MinValueValidate(value="1")
    @ExplainAnnotation (explain = "当前查询页码")
    private int pageNo = 1;

    @RangeValueValidate(max="20", min="1")
    @ExplainAnnotation (explain = "当前查询每页显示数")
    private int pageSize = 10;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getGcRoomId() {
        return gcRoomId;
    }

    public void setGcRoomId(String gcRoomId) {
        this.gcRoomId = gcRoomId;
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

    public GroupMemberListRequest(){

    }

    public GroupMemberListRequest(String memberId, String gcRoomId, int pageNo, int pageSize) {
        this.memberId = memberId;
        this.gcRoomId = gcRoomId;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "GroupManagerListRequest [gcRoomId=" + gcRoomId +",memberId="+memberId+"," +
                " pageSize="+pageSize+",pageNo="+pageNo+",super.toString() ]";
    }
}
