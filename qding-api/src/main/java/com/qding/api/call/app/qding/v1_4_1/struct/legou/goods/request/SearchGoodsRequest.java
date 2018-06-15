package com.qding.api.call.app.qding.v1_4_1.struct.legou.goods.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * Created by qd on 2015/11/4.
 */
@Validate
public class SearchGoodsRequest  extends BaseRequest {

    private static final long serialVersionUID = 95491467587244163L;


    /**
     * 社区ID
     */
    @NotNullValidate
    private Long projectId;

    /**
     * 会员ID
     */
    private String memberId;


    /**
     * 品类ID
     */
    private String revealCategoryId;

    /**
     * 关键词
     */
    @NotNullValidate
    private String keyWord;


    /**
     * 当前页码
     */
    @MinValueValidate(value="1")
    private Integer pageNo = 1;

    /**
     * 每页查询显示数
     */
    @RangeValueValidate(max="20", min="1")
    private Integer pageSize = 10;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getRevealCategoryId() {
        return revealCategoryId;
    }

    public void setRevealCategoryId(String revealCategoryId) {
        this.revealCategoryId = revealCategoryId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "SearchGoodsRequest [memberId="+memberId+",projectId=" + projectId +
                " ,revealCategoryId="+revealCategoryId+",keyWord="+keyWord+",pageNo="+pageNo+",pageSize="+pageSize+",super.toString() ]";
    }
}
