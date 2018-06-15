package com.qding.api.call.app.qding.v3_0_0.struct.project.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.rule.MaxLengthValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2017/2/23.
 */
public class GetActivityCalendarRequest  extends BaseRequest {

    private static final long serialVersionUID = -7677818023725387073L;

    @ExplainAnnotation (explain = "年份",desc = "4位")
    @NotNullValidate
    private Integer year;

    @ExplainAnnotation (explain = "月份")
    @NotNullValidate
    private Integer month;

    @ExplainAnnotation (explain = "月份")
    @NotNullValidate
    private String specifyData;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "GetActivityCalendarRequest{" +
                "year=" + year +
                ", month=" + month +
                '}';
    }
}
