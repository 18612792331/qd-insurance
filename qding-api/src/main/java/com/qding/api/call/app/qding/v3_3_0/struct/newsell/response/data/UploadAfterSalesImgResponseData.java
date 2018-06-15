package com.qding.api.call.app.qding.v3_3_0.struct.newsell.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;
import lombok.Getter;
import lombok.Setter;

public class UploadAfterSalesImgResponseData extends ResponseData {


    @Setter
    @Getter
    @ExplainAnnotation(explain = "图片url")
    private String imgUrl;

    @Setter
    @Getter
    @ExplainAnnotation(explain = "图片Id")
    private String imgId;
}
