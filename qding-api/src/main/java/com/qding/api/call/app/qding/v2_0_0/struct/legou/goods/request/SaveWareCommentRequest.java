package com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.OrderWareComment;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

import java.util.List;

/**
 * Created by qd on 2016/1/5.
 */
@Validate
public class SaveWareCommentRequest extends BaseRequest {

    private static final long serialVersionUID = -3349145165236305662L;

    @NotNullValidate
    @ExplainAnnotation(explain = "评价会员名称")
    private String memberName;

    @NotNullValidate
    @ExplainAnnotation(explain = "评价会员ID")
    private String memberId;

    @NotNullValidate
    @ExplainAnnotation(explain = "评价会员账号ID")
    private String userId;

    @NotNullValidate
    @ExplainAnnotation(explain = "待评价订单号")
    private String orderCode;

    @NotNullValidate
    @ExplainAnnotation(explain = "评价信息")
    private String comment;

    @ExplainAnnotation(explain = "是否匿名",desc = "1:显示 0：匿名")
    private Integer isAnonymity = 0;

    @NotNullValidate
    @ExplainAnnotation(explain = "商品评价信息")
    private List<OrderWareComment> list;

    public Integer getIsAnonymity() {
        return isAnonymity;
    }

    public void setIsAnonymity(Integer isAnonymity) {
        this.isAnonymity = isAnonymity;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<OrderWareComment> getList() {
        return list;
    }

    public void setList(List<OrderWareComment> list) {
        this.list = list;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "SaveWareCommentRequest{" +
                "memberName='" + memberName + '\'' +
                ", memberId='" + memberId + '\'' +
                ", userId='" + userId + '\'' +
                ", orderCode='" + orderCode + '\'' +
                ", comment='" + comment + '\'' +
                ", list=" + list +
                '}';
    }
}
