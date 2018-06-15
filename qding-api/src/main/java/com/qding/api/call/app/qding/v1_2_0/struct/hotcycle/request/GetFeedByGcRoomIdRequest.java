package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * Created by qd on 2015/10/29.
 */
@Validate
public class GetFeedByGcRoomIdRequest extends BaseRequest {

    private static final long serialVersionUID = -2289187638796264981L;

    @NotNullValidate
    private String gcRoomId;

    @MinValueValidate(value="1")
    private int pageNo = 1;

    @RangeValueValidate(max="20", min="1")
    private int pageSize = 10;

    /**
     * 上次刷新时间
     */
    private Long refreshTime;

    public Long getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(Long refreshTime) {
        this.refreshTime = refreshTime;
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

    @Override
    public String toString() {
        return "GetFeedByGcRoomIdRequest{" +
                "gcRoomId='" + gcRoomId + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", refreshTime=" + refreshTime +
                '}';
    }
}
