package com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by jiawenzheng on 2015/9/1.
 */

@Validate
public class GetGoodProjectAndCityBySkuIdRequest   extends BaseRequest {


    private static final long serialVersionUID = -3345023702659252797L;

    @NotNullValidate
    private Long skuId;

    private Long projectId;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    @Override
    public String toString() {
        return "GetGoodProjectAndCityBySkuIdRequest [skuId=" + skuId
                + ", projectId="+projectId+",toString()=" + super.toString() + "]";
    }
}
