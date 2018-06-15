package com.qding.api.call.app.qding.v3_0_0.struct.project.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2017/2/22.
 */
@Validate
public class AppNoticeUserDefineRequest extends BaseRequest {

    private static final long serialVersionUID = -1912633633914514205L;

    @NotNullValidate
    @ExplainAnnotation(explain = "公告ID")
    private String noticeId;

    @NotNullValidate
    @ExplainAnnotation(explain = "公告标题")
    private String noticeTitle;


    @NotNullValidate
    @ExplainAnnotation(explain = "喜好选项")
    private Integer userDef;

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public Integer getUserDef() {
        return userDef;
    }

    public void setUserDef(Integer userDef) {
        this.userDef = userDef;
    }
}
