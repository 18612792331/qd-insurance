package com.qding.api.call.app.qding.v3_3_0.struct.search;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.util.List;

/**
 * Created by qd on 2017/9/6.
 */
public class SearchItem extends SkipUrl {

    @ExplainAnnotation(explain = "搜索项图片")
    private String itemImg;

    @ExplainAnnotation(explain = "搜索项名称")
    private String itemName;

    @ExplainAnnotation(explain = "搜索项描述|副标题")
    private String itemDesc;

    @ExplainAnnotation(explain = "当前价格")
    private String itemPrice;

    @ExplainAnnotation(explain = "搜索项销售数量")
    private Integer itemShellCount;

    @ExplainAnnotation(explain = "乐购产品线名称")
    private String markingName;

    @ExplainAnnotation(explain = "可查看权限类型,0登录，1绑定房屋",desc="3.2新增")
    private Integer permType;

    @ExplainAnnotation(explain = "绑定房屋身份，业主，家庭成员，租客",desc="3.2新增")
    private List<String> bindRoomRole;

    @ExplainAnnotation(explain = "标签名称",desc="3.2新增")
    private String tagName;

    public Integer getPermType() {
        return permType;
    }

    public void setPermType(Integer permType) {
        this.permType = permType;
    }

    public List<String> getBindRoomRole() {
        return bindRoomRole;
    }

    public void setBindRoomRole(List<String> bindRoomRole) {
        this.bindRoomRole = bindRoomRole;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getMarkingName() {
        return markingName;
    }

    public void setMarkingName(String markingName) {
        this.markingName = markingName;
    }

    public String getItemImg() {
        return itemImg;
    }

    public void setItemImg(String itemImg) {
        this.itemImg = itemImg;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getItemShellCount() {
        return itemShellCount;
    }

    public void setItemShellCount(Integer itemShellCount) {
        this.itemShellCount = itemShellCount;
    }
}
