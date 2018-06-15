package com.qding.api.call.app.qding.v3_3_0.struct.search.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * Created by qd on 2017/9/7.
 */
@Validate
public class SearchItemsByTypeRequest extends BaseRequest {

    private static final long serialVersionUID = 8453679491966467097L;

    @ExplainAnnotation(explain = "搜索关键词")
    private String keyWord;

    @ExplainAnnotation(explain = "查询类型",desc = "2：乐购，3：服务，4：旅游，5：其他")
    @NotNullValidate
    private Integer searchType;

    @ExplainAnnotation(explain = "当前页")
    @MinValueValidate(value="1")
    private Integer pageNo = 1;

    @ExplainAnnotation(explain = "每页查询数")
    @RangeValueValidate(max="20", min="1")
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

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }
}
