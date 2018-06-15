package com.qding.api.call.app.qding.v3_0_0.struct.neighbor;

import java.util.List;

/**
 * Created by qd on 2017/3/7.
 */
public class PageResultDTO<T> {

    private List<T> list;

    private boolean haveNextPage;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public boolean isHaveNextPage() {
        return haveNextPage;
    }

    public void setHaveNextPage(boolean haveNextPage) {
        this.haveNextPage = haveNextPage;
    }
}
