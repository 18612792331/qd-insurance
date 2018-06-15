package com.qding.api.call.app.qding.v3_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.GrouponActivity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2017/2/28.
 */
public class WelfareBoard implements Serializable {

    private static final long serialVersionUID = -4699498990946000935L;


    @ExplainAnnotation(explain = "板块Code")
    private  String sectionCode;

    @ExplainAnnotation(explain = "排序索引")
    private Integer sortIndex;

    @ExplainAnnotation(explain = "标题")
    private String title;

    @ExplainAnnotation(explain = "阶梯团购")
    private GrouponActivity grouponActivity ;

    @ExplainAnnotation(explain = "秒杀|爆款特惠")
    private List<WelfareGoodsDTO> welfareGoodsList ;

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GrouponActivity getGrouponActivity() {
        return grouponActivity;
    }

    public void setGrouponActivity(GrouponActivity grouponActivity) {
        this.grouponActivity = grouponActivity;
    }

    public List<WelfareGoodsDTO> getWelfareGoodsList() {
        return welfareGoodsList;
    }

    public void setWelfareGoodsList(List<WelfareGoodsDTO> welfareGoodsList) {
        this.welfareGoodsList = welfareGoodsList;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }
}
