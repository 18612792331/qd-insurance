package com.qding.api.call.app.qding.v3_0_0.struct.user;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;

import static org.apache.solr.client.solrj.SolrQuery.ORDER.desc;

/**
 * Created by qd on 2017/7/12.
 */
public class GiftLableDTO implements Serializable {

    private static final long serialVersionUID = -9052761603782165784L;

    @ExplainAnnotation(explain = "是否显示提醒小红点",desc = "1:显示,0:不显示")
    private Integer showRemind = 0;

    @ExplainAnnotation(explain = "是否显示我的礼包",desc = "1:显示,0:不显示")
    private Integer hasGift = 0;

    public GiftLableDTO(){

    }

    public GiftLableDTO(Integer showRemind, Integer hasGift) {
        this.showRemind = showRemind;
        this.hasGift = hasGift;
    }

    public Integer getShowRemind() {
        return showRemind;
    }

    public void setShowRemind(Integer showRemind) {
        this.showRemind = showRemind;
    }

    public Integer getHasGift() {
        return hasGift;
    }

    public void setHasGift(Integer hasGift) {
        this.hasGift = hasGift;
    }
}
