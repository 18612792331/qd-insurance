package com.qding.api.call.app.qding.v3_0_0.struct.legou.goods.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * Created by qd on 2015/11/4.
 */
@Validate
public class SearchGoodsRequest extends BaseRequest {

    private static final long serialVersionUID = 95491467587244163L;


    @ExplainAnnotation(explain = "品类ID")
    private String revealCategoryId;

    @NotNullValidate
    @ExplainAnnotation(explain = "关键词")
    private String keyWord;

    @MinValueValidate(value="1")
    @ExplainAnnotation(explain = "当前查询页码")
    private Integer pageNo = 1;

    @RangeValueValidate(max="20", min="1")
    @ExplainAnnotation(explain = "每页查询数")
    private Integer pageSize = 10;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    public String getRevealCategoryId() {
        return revealCategoryId;
    }

    public void setRevealCategoryId(String revealCategoryId) {
        this.revealCategoryId = revealCategoryId;
    }

    @Override
    public String toString() {
        return "SearchGoodsRequest [ revealCategoryId="+revealCategoryId+",keyWord="+keyWord+",pageNo="+pageNo+",pageSize="+pageSize+",super.toString() ]";
    }
}
