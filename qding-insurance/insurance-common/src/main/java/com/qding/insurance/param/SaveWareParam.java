package com.qding.insurance.param;

import java.util.Date;
import java.util.List;

import com.qding.insurance.domain.InsuranceProject;
import com.qding.insurance.domain.InsuranceWareExtWithBLOBs;

public class SaveWareParam extends InsuranceWareExtWithBLOBs {

    private static final long serialVersionUID = -7275592118437958837L;

    private List<InsuranceProject> projectList;

    private String onlineTimeStr;

    private String offlineTimeStr;

    public String getOnlineTimeStr() {
        return onlineTimeStr;
    }

    public void setOnlineTimeStr(String onlineTimeStr) {
        this.onlineTimeStr = onlineTimeStr;
    }

    public String getOfflineTimeStr() {
        return offlineTimeStr;
    }

    public void setOfflineTimeStr(String offlineTimeStr) {
        this.offlineTimeStr = offlineTimeStr;
    }

    public List<InsuranceProject> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<InsuranceProject> projectList) {
        this.projectList = projectList;
    }

}
