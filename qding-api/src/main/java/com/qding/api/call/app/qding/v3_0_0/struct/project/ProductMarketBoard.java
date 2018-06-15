package com.qding.api.call.app.qding.v3_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.BoardImg;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;
import java.util.List;

/**
 * 4产品营销位
 * Created by qd on 2017/3/1.
 */
public class ProductMarketBoard extends SkipUrl implements Serializable {

    private static final long serialVersionUID = 6007835086294777107L;

    @ExplainAnnotation(explain = "板块Code")
    private  String sectionCode;

    @ExplainAnnotation(explain = "排序索引")
    private Integer sortIndex;


    @ExplainAnnotation(explain = "产品营销位")
    private List<BoardImg> productList;

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public List<BoardImg> getProductList() {
        return productList;
    }

    public void setProductList(List<BoardImg> productList) {
        this.productList = productList;
    }
}
