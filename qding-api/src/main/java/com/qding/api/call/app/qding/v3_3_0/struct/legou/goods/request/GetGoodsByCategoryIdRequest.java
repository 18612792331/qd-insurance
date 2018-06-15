package com.qding.api.call.app.qding.v3_3_0.struct.legou.goods.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;
import lombok.Getter;
import lombok.Setter;

public class GetGoodsByCategoryIdRequest  extends BaseRequest {

    private static final long serialVersionUID = 1441496984810728063L;

    @ExplainAnnotation(explain = "品类ID")
    @Setter
    @Getter
    private String revealCategoryId;

    @ExplainAnnotation(explain = "供货商编号",desc = "JD;YX")
    @Setter
    @Getter
    private String supplierNo;

    @MinValueValidate(value = "1")
    @ExplainAnnotation(explain = "当前页码")
    @Setter
    @Getter
    private Integer pageNo = 1;

    @RangeValueValidate(max = "20", min = "1")
    @ExplainAnnotation(explain = "每页查询显示数")
    @Setter
    @Getter
    private Integer pageSize = 10;

    /**
     * 1:最新上架时间,2:销量最好(总销量（实际销量+虚拟销量）desc, 实际销量 desc, 虚拟销量 desc),3:价格(销售价格 asc),4:价格(销售价格 desc)
     */
    @ExplainAnnotation(explain = "查询排序类型", desc = "1:最新上架,2:销量最好,3:价格")
    @Setter
    @Getter
    private Integer sortType;


    @Override
    public String toString() {
        return "GetGoodsByCategoryIdRequest{" +
                "revealCategoryId='" + revealCategoryId + '\'' +
                ", supplierNo='" + supplierNo + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", sortType=" + sortType +
                '}';
    }
}
