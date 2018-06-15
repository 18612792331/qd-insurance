package com.qding.api.call.app.qding.v1_4_1.struct.hotcycle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2015/10/27.
 */
public class JoinGroupApplyUserList implements Serializable {

    private static final long serialVersionUID = 7368067734105366926L;

    private Long totalCount;

    private List<JoinGroupApplyUser> list;

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<JoinGroupApplyUser> getList() {
        return list;
    }

    public void setList(List<JoinGroupApplyUser> list) {
        this.list = list;
    }
}
