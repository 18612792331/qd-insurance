package com.qding.insurance.param;

import java.io.Serializable;

public class PageParam implements Serializable{

    private Integer page = 1;

    private Integer size = 20;

    private Integer offset;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getOffset() {

        offset = (page - 1) * size;

        return offset;
    }

}
