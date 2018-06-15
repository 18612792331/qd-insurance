package com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.wheel;

import java.util.List;

public class Marketing {

    private MarketingInfo marketingInfo;

    private List<RegionInfo> regions;

    private List<ProjectInfo> projects;

    private LimitCondition limitCondition;

    public MarketingInfo getMarketingInfo() {
        return marketingInfo;
    }

    public void setMarketingInfo(MarketingInfo marketingInfo) {
        this.marketingInfo = marketingInfo;
    }

    public List<RegionInfo> getRegions() {
        return regions;
    }

    public void setRegions(List<RegionInfo> regions) {
        this.regions = regions;
    }

    public List<ProjectInfo> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectInfo> projects) {
        this.projects = projects;
    }

    public LimitCondition getLimitCondition() {
        return limitCondition;
    }

    public void setLimitCondition(LimitCondition limitCondition) {
        this.limitCondition = limitCondition;
    }

}
