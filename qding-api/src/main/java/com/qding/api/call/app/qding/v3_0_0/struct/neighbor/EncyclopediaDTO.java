package com.qding.api.call.app.qding.v3_0_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2017/3/4.
 */
public class EncyclopediaDTO extends BriefEncyclopedia  implements Serializable{

    private static final long serialVersionUID = 8577862771947388742L;

    @ExplainAnnotation (explain = "百科详情，相关推荐")
    private List<RecommendEncyclopediaDTO> recommendList;


    public List<RecommendEncyclopediaDTO> getRecommendList() {
        return recommendList;
    }

    public void setRecommendList(List<RecommendEncyclopediaDTO> recommendList) {
        this.recommendList = recommendList;
    }
}
