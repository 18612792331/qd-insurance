package com.qding.api.call.app.qding.v2_0_0.struct.project.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.RecommendBoard;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2015/12/30.
 */
public class GetRecommendBoardResponseData extends ResponseData {

    private static final long serialVersionUID = -2144500563804556389L;

    @ExplainAnnotation(explain = "推荐板块")
    private RecommendBoard recommendBoard;

    public RecommendBoard getRecommendBoard() {
        return recommendBoard;
    }

    public void setRecommendBoard(RecommendBoard recommendBoard) {
        this.recommendBoard = recommendBoard;
    }

    @Override
    public String toString() {
        return "GetRecommendBoardResponseData{" +
                "recommendBoard=" + recommendBoard +
                '}';
    }
}
