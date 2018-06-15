package com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MaxLengthValidate;
import com.qding.framework.common.smart.validate.rule.MinLengthValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/10/27.
 */
@Validate
public class NewGroupNoticeRequest  extends BaseRequest {

    private static final long serialVersionUID = 8371554634220581588L;

    /**
     * 群组ID
     */
    @NotNullValidate
    private String gcRoomId;


    /**
     * 公告创建者名称
     */
    @NotNullValidate
    private String createAt;

    /**
     * 公告内容
     */
    @MaxLengthValidate(length = 500)
    private String content;

    /**
     * 公告标题
     */
    @MaxLengthValidate(length = 500)
    @MinLengthValidate(length =5)
    private String name;

    /**
     * 公告图片
     */
    @NotNullValidate
    private String[] imgs;

    public String getGcRoomId() {
        return gcRoomId;
    }

    public void setGcRoomId(String gcRoomId) {
        this.gcRoomId = gcRoomId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String[] getImgs() {
        return imgs;
    }

    public void setImgs(String[] imgs) {
        this.imgs = imgs;
    }

    @Override
    public String toString() {
        return "NewGroupNoticeRequest [gcRoomId=" + gcRoomId +",createAt="+createAt+",content="+content +
                " ,name="+name+",imgs="+imgs+",super.toString() ]";
    }
}
