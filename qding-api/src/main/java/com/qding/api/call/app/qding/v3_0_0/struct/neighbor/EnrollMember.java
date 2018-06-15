package com.qding.api.call.app.qding.v3_0_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.BriefMember;

/**
 * Created by qd on 2017/5/18.
 */
public class EnrollMember extends BriefMember {

    private static final long serialVersionUID = 7593457225866233797L;

    @ExplainAnnotation(explain = "计划人数")
    private int personCount;

    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }


}
