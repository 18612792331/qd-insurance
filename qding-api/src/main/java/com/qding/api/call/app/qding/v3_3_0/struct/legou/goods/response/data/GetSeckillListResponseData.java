package com.qding.api.call.app.qding.v3_3_0.struct.legou.goods.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_3_0.struct.legou.goods.Seckill;
import com.qding.api.struct.ResponseData;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class GetSeckillListResponseData  extends ResponseData {

    private static final long serialVersionUID = 7381712102189436471L;


    @ExplainAnnotation(explain = "秒杀列表")
    @Getter
    @Setter
    private List<Seckill> list;

    @ExplainAnnotation(explain = "总秒杀数")
    @Getter
    @Setter
    private Integer totalCount;


    @Override
    public String toString() {
        return "GetSeckillListResponseData{" +
                "list=" + list +
                ", totalCount=" + totalCount +
                '}';
    }
}
