package com.qding.api.call.app.qding.v4_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;
import java.io.Serializable;
import java.util.List;

/**
 * 轮播图 Banner
 * Created by qd on 2018/4/18.
 */
public class BannerBoard implements Serializable {

    private static final long serialVersionUID = 6007835086294777107L;

    @ExplainAnnotation(explain = "板块Code")
    private  String sectionCode;

    @ExplainAnnotation(explain = "排序索引")
    private Integer sortIndex;

    @ExplainAnnotation(explain = "轮播图")
    private List<IndexBanner> bannerList;

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

	public List<IndexBanner> getBannerList() {
		return bannerList;
	}

	public void setBannerList(List<IndexBanner> bannerList) {
		this.bannerList = bannerList;
	}

}
