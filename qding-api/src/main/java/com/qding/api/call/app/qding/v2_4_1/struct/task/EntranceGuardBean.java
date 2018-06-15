package com.qding.api.call.app.qding.v2_4_1.struct.task;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;
import java.io.Serializable;

/**
 * Created by qd on 2016/3/24.
 */
public class EntranceGuardBean extends  SkipUrl  implements Serializable {

    private static final long serialVersionUID = 2490513290208029046L;

    @ExplainAnnotation (explain = "弹框信息")
    private DialogInfo dialogEntity;

    @ExplainAnnotation (explain = "是否弹框",desc = "1:显示 ,0:不显示,2:无活动或已参与过活动")
    private Integer showDialog;

    @ExplainAnnotation (explain = "弹出层显示秒数")
    private Integer timeoutInterval;

    public Integer getTimeoutInterval() {
        return timeoutInterval;
    }

    public void setTimeoutInterval(Integer timeoutInterval) {
        this.timeoutInterval = timeoutInterval;
    }

    public DialogInfo getDialogEntity() {
        return dialogEntity;
    }

    public void setDialogEntity(DialogInfo dialogEntity) {
        this.dialogEntity = dialogEntity;
    }

    public Integer getShowDialog() {
        return showDialog;
    }

    public void setShowDialog(Integer showDialog) {
        this.showDialog = showDialog;
    }
}
