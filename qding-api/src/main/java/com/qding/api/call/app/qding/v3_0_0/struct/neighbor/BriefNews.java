package com.qding.api.call.app.qding.v3_0_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_3_0.struct.neighbor.RecommendDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2017/3/4.
 */
public class BriefNews implements Serializable {

    private static final long serialVersionUID = 4560604407692274660L;

    @ExplainAnnotation(explain = "新闻类别id")
    private String newsTypeId;
    
    @ExplainAnnotation(explain = "新闻类别名称")
    private String newsType;

    @ExplainAnnotation(explain = "新闻权重")
    private Integer newsWeight;

    @ExplainAnnotation(explain = "新闻来源")
    private String newsSource;

    @ExplainAnnotation(explain = "新闻关键词")
    private String newsKeywords;
    
    @ExplainAnnotation (explain = "新闻相关推荐 内容列表", desc="3.3版本新增")
    private List<RecommendDto> recommendList;
    

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public Integer getNewsWeight() {
        return newsWeight;
    }

    public void setNewsWeight(Integer newsWeight) {
        this.newsWeight = newsWeight;
    }

    public String getNewsSource() {
        return newsSource;
    }

    public void setNewsSource(String newsSource) {
        this.newsSource = newsSource;
    }

    public String getNewsKeywords() {
        return newsKeywords;
    }

    public void setNewsKeywords(String newsKeywords) {
        this.newsKeywords = newsKeywords;
    }

	public List<RecommendDto> getRecommendList() {
		return recommendList;
	}

	public void setRecommendList(List<RecommendDto> recommendList) {
		this.recommendList = recommendList;
	}

	public String getNewsTypeId() {
		return newsTypeId;
	}

	public void setNewsTypeId(String newsTypeId) {
		this.newsTypeId = newsTypeId;
	}
    
	
	
    
    
}
