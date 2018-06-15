package com.qding.api.call.app.qding.v3_2_0.struct.project.response;

import com.qding.api.call.app.qding.v3_2_0.struct.project.ActivityH5InfoDto;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by jinhaishan on 17/7/17.
 */
public class GetActivityInfoResponseData extends ResponseData {

    private static final long serialVersionUID = -2814791023183645290L;

    private String title;

    private List<ActivityH5InfoDto> activityH5InfoDtos;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ActivityH5InfoDto> getActivityH5InfoDtos() {
        return activityH5InfoDtos;
    }

    public void setActivityH5InfoDtos(List<ActivityH5InfoDto> activityH5InfoDtos) {
        this.activityH5InfoDtos = activityH5InfoDtos;
    }
}
