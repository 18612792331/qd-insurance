package com.qding.api.call.app.qding.v3_3_0.struct.legou.goods.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.CategoryEntity;
import com.qding.api.struct.ResponseData;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class GetShowCategoryResponseData extends ResponseData {

    private static final long serialVersionUID = 3802748064596538116L;


    @ExplainAnnotation (explain = "展示品类列表")
    @Setter
    @Getter
    private List<CategoryEntity> categorList;
}
