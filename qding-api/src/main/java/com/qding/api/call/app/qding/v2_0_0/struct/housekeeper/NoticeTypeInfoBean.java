package com.qding.api.call.app.qding.v2_0_0.struct.housekeeper;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2015/12/31.
 */
public class NoticeTypeInfoBean implements Serializable {

    private static final long serialVersionUID = 6169420574459647364L;

    public NoticeTypeInfoBean() {
    }

    @ExplainAnnotation(explain = "公告类型")
    private Integer noticeType;

    @ExplainAnnotation(explain = "公告类型名称")
    private String noticeName;

    @ExplainAnnotation(explain = "公告类型所属颜色")
    private String noticeColor;

    public Integer getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(Integer noticeType) {
        this.noticeType = noticeType;
    }

    public String getNoticeName() {
        return noticeName;
    }

    public void setNoticeName(String noticeName) {
        this.noticeName = noticeName;
    }

    public String getNoticeColor() {
        return noticeColor;
    }

    public void setNoticeColor(String noticeColor) {
        this.noticeColor = noticeColor;
    }
}
