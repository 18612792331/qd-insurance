package com.qding.api.call.app.qding.v3_3_0.struct.neighbor;

import java.io.Serializable;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

/**
 * 引用贴
 * @author Administrator
 *
 */
public class ReferenceTopicDto extends SkipUrl implements Serializable {

    private static final long serialVersionUID = -7086892326328364732L;

    @ExplainAnnotation(explain = "头图")
    private String topicImage;

    @ExplainAnnotation(explain = "标题")
    private String topicTitle;

    @ExplainAnnotation(explain = "帖子类型",desc="新版子分类：1：晒图话题，2：讨论型话题，3：投票型话题, 4：报名  5:邻里互动，6,：新闻，7：百科")
    private String topicType;


    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }


	public String getTopicImage() {
		return topicImage;
	}

	public void setTopicImage(String topicImage) {
		this.topicImage = topicImage;
	}

	public String getTopicType() {
		return topicType;
	}

	public void setTopicType(String topicType) {
		this.topicType = topicType;
	}

    
	
	
	
	
	
	
	
    
    
}
