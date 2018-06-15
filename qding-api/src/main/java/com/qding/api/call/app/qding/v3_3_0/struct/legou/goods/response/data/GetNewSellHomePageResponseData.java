package com.qding.api.call.app.qding.v3_3_0.struct.legou.goods.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_2_0.struct.project.GrouponActivity;
import com.qding.api.call.app.qding.v3_3_0.struct.legou.goods.MarketingBoard;
import com.qding.api.call.app.qding.v3_3_0.struct.legou.goods.*;
import com.qding.api.struct.ResponseData;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class GetNewSellHomePageResponseData  extends ResponseData {

    private static final long serialVersionUID = -7074472800044580153L;

    @ExplainAnnotation(explain = "轮播图")
    @Setter
    @Getter
    private List<NewSellBanner> bannerBoard;


    @ExplainAnnotation(explain = "栏目列表")
    @Setter
    @Getter
    private List<NewSellColumn> columnBoard;

    @ExplainAnnotation(explain = "驿站板块")
    @Setter
    @Getter
    private NewSellStage stageBroad;

    @ExplainAnnotation(explain = "资源位板块")
    @Setter
    @Getter
    private List<MarketingBoard> marketingBoard;

    @ExplainAnnotation(explain = "阶梯团购")
    @Setter
    @Getter
    private GrouponActivity grouponBoard;

    @ExplainAnnotation(explain = "品类推荐")
    @Setter
    @Getter
    private List<CategoryRecommend> categoryBoard;

    @ExplainAnnotation(explain = "内容栏列表")
    @Setter
    @Getter
    private List<ContentColumn> contentBoard;

    @Override
    public String toString() {
        return "GetNewSellHomePageResponseData{" +
                "bannerBoard=" + bannerBoard +
                ", columnBoard=" + columnBoard +
                ", stageBroad=" + stageBroad +
                ", marketingBoard=" + marketingBoard +
                ", categoryBoard=" + categoryBoard +
                ", contentBoard=" + contentBoard +
                '}';
    }
}
