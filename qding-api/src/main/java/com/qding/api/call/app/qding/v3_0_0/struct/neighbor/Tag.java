package com.qding.api.call.app.qding.v3_0_0.struct.neighbor;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;
import com.qding.api.util.APIPropertiesClient;

/**
 * Created by qd on 2016/11/22.
 */
public class Tag extends SkipUrl  implements Serializable {

    private static final long serialVersionUID = 1911087656742581664L;

    @ExplainAnnotation(explain = "标签ID")
    private String tagId;

    @ExplainAnnotation (explain = "标签名称")
    private String tagName;

    @ExplainAnnotation (explain = "标签图片")
    private String tagImg;

    @ExplainAnnotation (explain = "标签描述")
    private String tagDesc;

    @ExplainAnnotation (explain = "标签状态",desc = "1:上线")
    private Integer tagStatus;
    
    @ExplainAnnotation (explain = "标签聚合页分享URL")
    private String shareUrl=APIPropertiesClient.getLableShareUrl();

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagImg() {
        return tagImg;
    }

    public void setTagImg(String tagImg) {
        this.tagImg = tagImg;
    }

    public String getTagDesc() {
        return tagDesc;
    }

    public void setTagDesc(String tagDesc) {
        this.tagDesc = tagDesc;
    }

    public Integer getTagStatus() {
        return tagStatus;
    }

    public void setTagStatus(Integer tagStatus) {
        this.tagStatus = tagStatus;
    }

	public String getShareUrl() {
		if(StringUtils.isNotBlank(tagId)){
			return shareUrl+tagId;
		}
		return null;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}
    
    
    
}
