package com.qding.api.imessage;

import java.io.Serializable;

/**
 * Created by qd on 2015/11/6.
 */
public class KeywordNotify implements Serializable {

    private Long projectId;

    private String keyword;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
