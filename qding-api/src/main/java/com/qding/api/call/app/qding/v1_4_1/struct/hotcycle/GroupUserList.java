package com.qding.api.call.app.qding.v1_4_1.struct.hotcycle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2015/10/27.
 */
public class GroupUserList implements Serializable {

    private static final long serialVersionUID = 3557162961017006482L;

    private Long totalCount;

    private List<GroupUser> list;

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<GroupUser> getList() {
        return list;
    }

    public void setList(List<GroupUser> list) {
        this.list = list;
    }
}
