package com.qding.api.call.app.qding.v3_0_0.struct.brick.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.smart.validate.rule.WalletStatusValidate;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

@Validate
public class GetProjectListByKeyWordRequest extends BaseRequest {

    private static final long serialVersionUID = 5470457274968123887L;

    @NotNullValidate
    @ExplainAnnotation(explain = "社区搜索关键词")
    private String keyWord;


    @MinValueValidate(value="1")
    @ExplainAnnotation(explain = "当前 查询页码")
    private Integer pageNo =1;

    @RangeValueValidate(max="100", min="1")
    @ExplainAnnotation(explain = "社区搜索关键词")
    private Integer pageSize =100;

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

    @Override
    public String toString() {
        return "GetProjectListByKeyWordRequest{" +
                "keyWord='" + keyWord + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }
}
