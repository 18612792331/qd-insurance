package com.qding.api.call.app.qding.v2_5_0.struct.housekeeper.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 根据roomId查询物业费缴费详情
 *
 * @author lichao
 */
@Validate
public class GetRoomPropertyBillsRequest extends BaseRequest {

    /**
     *
     */
    private static final long serialVersionUID = -7722980109485018092L;

    @ExplainAnnotation(explain = "月份", desc = "例:1422720000000")
    private Long month;

    @NotNullValidate
    @ExplainAnnotation(explain = "房间ID")
    private String roomId;

    //兼容时区问题 yyyy-MM-dd
    @ExplainAnnotation(explain = "查询时间", desc = "兼容时区问题 yyyy-MM-dd")
    private String date;

    //兼容时区问题 yyyy-MM-dd
    @ExplainAnnotation(explain = "查询的哪个月份", desc = "取当前列表返回的feeDueDateStrMonth字段")
    private String monthStr;

    @ExplainAnnotation(explain = "memberId")
    private String memberId;

    @NotNullValidate
    @ExplainAnnotation(explain = "支付状态", desc = "0:待缴 | 1：已缴")
    private String payStatus;

    public GetRoomPropertyBillsRequest() {

    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMonth(Long month) {
        this.month = month;
    }

    public Long getMonth() {
        return month;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }


    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getMonthStr() {
        return monthStr;
    }

    public void setMonthStr(String monthStr) {
        this.monthStr = monthStr;
    }

    @Override
    public String toString() {
        return "GetRoomPropertyBillsRequest{" +
                "month=" + month +
                ", roomId='" + roomId + '\'' +
                ", date='" + date + '\'' +
                ", memberId='" + memberId + '\'' +
                ", payStatus='" + payStatus + '\'' +
                ", monthStr='" + monthStr + '\'' +
                '}';
    }
}
