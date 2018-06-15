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
public class GroupNoticeListRequest  extends BaseRequest {

    private static final long serialVersionUID = -6591212501285263962L;

    @NotNullValidate
    private String gcRoomId;

    @NotNullValidate
    private String userId;

    @MinValueValidate(value="1")
    private int pageNo = 1;

    @RangeValueValidate(max="20", min="1")
    private int pageSize = 10;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "GroupNoticeListRequest [userId="+userId+",gcRoomId=" + gcRoomId +",pageNo="+pageNo+",pageSize="+pageSize +
                " ,super.toString() ]";
    }
}
