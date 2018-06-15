package com.qding.api.call.app.qding.v1_3_2.struct.hotcycle;

import java.io.Serializable;

/**
 * Created by jiawenzheng on 2015/7/17.
 */
public class HcChartlet implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 4062835288239584517L;

	/**
     * 贴图ID
     */
    private String chartletId;

    /**
     * 贴图图片路径
     */
    private String chartletImageUrl;


    public String getChartletId() {
        return chartletId;
    }

    public void setChartletId(String chartletId) {
        this.chartletId = chartletId;
    }

    public String getChartletImageUrl() {
        return chartletImageUrl;
    }

    public void setChartletImageUrl(String chartletImageUrl) {
        this.chartletImageUrl = chartletImageUrl;
    }

  
}
