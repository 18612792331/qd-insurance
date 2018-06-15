package com.qding.api.call.app.qding.v1_4_1.struct.legou.goods.response.data;

import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.Goods;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2015/11/4.
 */
public class SearchGoodsResponseData extends ResponseData {

    private static final long serialVersionUID = 5924774001093082799L;

    /**
     * 当前记录数
     */
    private Long recordCount;

    /**
     * 总记录数
     */
    private Long totalCount;

    /**
     * 商品信息对象列表
     */
    private List<Goods> list;

    public Long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Long recordCount) {
        this.recordCount = recordCount;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<Goods> getList() {
        return list;
    }

    public void setList(List<Goods> list) {
        this.list = list;
    }

    public SearchGoodsResponseData(Long recordCount, Long totalCount,
                                      List<Goods> list) {
        super();
        this.recordCount = recordCount;
        this.totalCount = totalCount;
        this.list = list;
    }

    public SearchGoodsResponseData(){

    }

    @Override
    public String toString() {
        return "SearchGoodsResponseData [recordCount=" + recordCount
                + ", totalCount=" + totalCount + ", list=" + list
                + ", toString()="+ super.toString() + "]";
    }

}
