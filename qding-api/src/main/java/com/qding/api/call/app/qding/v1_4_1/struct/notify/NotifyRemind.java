package com.qding.api.call.app.qding.v1_4_1.struct.notify;

import java.io.Serializable;

/**
 * Created by qd on 2015/11/2.
 */
public class NotifyRemind implements Serializable {

    private static final long serialVersionUID = -2931071200859919299L;

    private Integer queryType;

    private String content;

    private Integer noticeNum;

    public Integer getQueryType() {
        return queryType;
    }

    public void setQueryType(Integer queryType) {
        this.queryType = queryType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getNoticeNum() {
        return noticeNum;
    }

    public void setNoticeNum(Integer noticeNum) {
        this.noticeNum = noticeNum;
    }
}
