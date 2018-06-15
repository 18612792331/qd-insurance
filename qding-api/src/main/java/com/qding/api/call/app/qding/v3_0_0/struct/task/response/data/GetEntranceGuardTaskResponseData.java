package com.qding.api.call.app.qding.v3_0_0.struct.task.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_0_0.struct.task.EntranceGuardActivity;
import com.qding.api.call.app.qding.v3_0_0.struct.task.MaketingActivity;
import com.qding.api.call.app.qding.v3_0_0.struct.task.DialogInfo;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2016/3/24.
 */
public class GetEntranceGuardTaskResponseData extends ResponseData {

    private static final long serialVersionUID = -2419835841594744402L;

    @ExplainAnnotation (explain = "弹框信息")
    private DialogInfo dialogEntity;

    @ExplainAnnotation (explain = "门禁活动")
    private EntranceGuardActivity entranceGuard;

    @ExplainAnnotation (explain = "首页整合营销活动")
    private MaketingActivity marketing;

    public EntranceGuardActivity getEntranceGuard() {
        return entranceGuard;
    }

    public void setEntranceGuard(EntranceGuardActivity entranceGuard) {
        this.entranceGuard = entranceGuard;
    }

    public MaketingActivity getMarketing() {
        return marketing;
    }

    public void setMarketing(MaketingActivity marketing) {
        this.marketing = marketing;
    }

    public DialogInfo getDialogEntity() {
        return dialogEntity;
    }

    public void setDialogEntity(DialogInfo dialogEntity) {
        this.dialogEntity = dialogEntity;
    }


    @Override
    public String toString() {
        return "GetEntranceGuardTaskResponseData{" +
                "dialogEntity=" + dialogEntity +
                '}';
    }
}
