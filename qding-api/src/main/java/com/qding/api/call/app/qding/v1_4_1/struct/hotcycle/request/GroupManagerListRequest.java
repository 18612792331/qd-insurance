package com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * Created by qd on 2015/10/27.
 */

@Validate
public class GroupManagerListRequest  extends BaseRequest {

    private static final long serialVersionUID = 1932072619466809090L;

    @NotNullValidate
    private String userId;

    @NotNullValidate
    private String gcRoomId;

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

    public GroupManagerListRequest(){

    }

    public GroupManagerListRequest(String userId, String gcRoomId, int pageNo, int pageSize) {
        this.userId = userId;
        this.gcRoomId = gcRoomId;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "GroupManagerListRequest [gcRoomId=" + gcRoomId +",userId="+userId+"," +
                " pageSize="+pageSize+",pageNo="+pageNo+",super.toString() ]";
    }
}
