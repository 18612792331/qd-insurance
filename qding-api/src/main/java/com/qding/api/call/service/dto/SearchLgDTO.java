package com.qding.api.call.service.dto;

import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.Goods;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2017/9/13.
 */
public class SearchLgDTO implements Serializable {

    private static final long serialVersionUID = -8785691247313873968L;

    /**
     * 搜索相关列表
     */
    private List<Goods> list;

    /**
     * 搜索出总数
     */
    private Long totalCount;

    public List<Goods> getList() {
        return list;
    }

    public void setList(List<Goods> list) {
        this.list = list;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }
}
