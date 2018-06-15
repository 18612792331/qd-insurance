package com.qding.api.call.app.qding.v3_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.BtnSkip;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;

/**
 * Created by qd on 2017/2/28.
 */
@ExplainAnnotation(explain = "活动日历")
public class ActivityCalendarDTO extends BtnSkip implements Serializable {

    @ExplainAnnotation(explain = "活动ID")
    private String activityId;

    @ExplainAnnotation(explain = "活动图标")
    private String activityIco;

    @ExplainAnnotation(explain = "活动名称")
    private String activityName;

    @ExplainAnnotation(explain = "活动时间")
    private String activityTime;

    @ExplainAnnotation(explain = "整合营销标题",desc = "用于友盟统计")
    private String title;

 /*   @ExplainAnnotation(explain = "整合营销分享信息")
    private CalendarShareDTO calendarShare;*/

    public String getActivityIco() {
        return activityIco;
    }

    public void setActivityIco(String activityIco) {
        this.activityIco = activityIco;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    /*    public CalendarShareDTO getCalendarShare() {
        return calendarShare;
    }

    public void setCalendarShare(CalendarShareDTO calendarShare) {
        this.calendarShare = calendarShare;
    }*/
}
