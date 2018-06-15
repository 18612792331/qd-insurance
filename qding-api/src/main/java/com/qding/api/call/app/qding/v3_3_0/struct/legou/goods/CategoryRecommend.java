package com.qding.api.call.app.qding.v3_3_0.struct.legou.goods;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class CategoryRecommend  implements Serializable {

    private static final long serialVersionUID = -2431984366194206350L;

    @ExplainAnnotation(explain = "品类ID")
    @Getter
    @Setter
    private String categoryId;

    @ExplainAnnotation(explain = "品类图片")
    @Getter
    @Setter
    private String categoryImg;

    @ExplainAnnotation(explain = "品类名称")
    @Getter
    @Setter
    private String categoryName;

}
