package com.qding.api.call.app.qding.v1_3_0.struct.brick.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.Dictonary;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/6/14.
 */
public class GetEvaluationLabelDicResponseData extends ResponseData {

    private static final long serialVersionUID = 4697128997193810860L;

    private List<Dictonary> evaluationLabel;

    public List<Dictonary> getEvaluationLabel() {
        return evaluationLabel;
    }

    public void setEvaluationLabel(List<Dictonary> evaluationLabel) {
        this.evaluationLabel = evaluationLabel;
    }

    @Override
    public String toString() {
        return "GetEvaluationLabelDicResponseData{" +
                "evaluationLabel=" + evaluationLabel +
                '}';
    }
}
