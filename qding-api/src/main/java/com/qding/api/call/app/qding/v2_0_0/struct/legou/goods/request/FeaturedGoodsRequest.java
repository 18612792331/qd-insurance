package com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * Created by qd on 2015/12/21.
 */

@Validate
public class FeaturedGoodsRequest extends BaseRequest {

    private static final long serialVersionUID = 6823591690789478038L;

    @MinValueValidate(value="1")
    @ExplainAnnotation(explain = "当前查询页码")
    private int pageNo = 1;

    @RangeValueValidate(max="20", min="1")
    @ExplainAnnotation(explain = "每页查询数")
    private int pageSize = 10;

    @NotNullValidate
    @ExplainAnnotation(explain = "推荐ID")
    private String recommendId;

    public String getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(String recommendId) {
        this.recommendId = recommendId;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "FeaturedGoodsResponseData [pageNo=" + pageNo +",pageSize="+pageSize+",recommendId="+recommendId+",toString="+ super.toString()+"]";
    }


}
