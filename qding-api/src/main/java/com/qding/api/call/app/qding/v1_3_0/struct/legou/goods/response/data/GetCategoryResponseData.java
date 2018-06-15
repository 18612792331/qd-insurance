package com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.response.data;

import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.CategoryEntity;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/1/8.
 */
public class GetCategoryResponseData extends ResponseData {

    private static final long serialVersionUID = -7656125318649370511L;

    /**
     * 品类对象列表
     */
    public  List<CategoryEntity> list;

    public List<CategoryEntity> getList() {
        return list;
    }

    public void setList(List<CategoryEntity> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "GetCategoryResponseData{" +
                "list=" + list +
                '}';
    }
}
