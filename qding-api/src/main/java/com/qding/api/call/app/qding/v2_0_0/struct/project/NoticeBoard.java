package com.qding.api.call.app.qding.v2_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.Notice;
import com.qding.api.call.app.qding.v2_0_0.struct.housekeeper.NoticeTypeInfoBean;
import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2015/12/22.
 */
public class NoticeBoard extends SkipUrl implements Serializable {

    private static final long serialVersionUID = -4930554026059580261L;

    @ExplainAnnotation(explain = "板块Code")
    private  String sectionCode;

    @ExplainAnnotation(explain = "排序索引")
    private Integer sortIndex;

    @ExplainAnnotation(explain = "提示图",desc = "2.8版本新增")
    private String remindImg;

    @ExplainAnnotation(explain = "公告类型字典列表")
    private List<NoticeTypeInfoBean> noticeTypeList ;

    @ExplainAnnotation(explain = "公告列表")
    private List<Notice> list;

    public List<Notice> getList() {
        return list;
    }

    public void setList(List<Notice> list) {
        this.list = list;
    }

    public List<NoticeTypeInfoBean> getNoticeTypeList() {
        return noticeTypeList;
    }

    public void setNoticeTypeList(List<NoticeTypeInfoBean> noticeTypeList) {
        this.noticeTypeList = noticeTypeList;
    }

    public String getRemindImg() {
        return remindImg;
    }

    public void setRemindImg(String remindImg) {
        this.remindImg = remindImg;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }
}
