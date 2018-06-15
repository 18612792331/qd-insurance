package com.qding.api.call.app.qding.v1_4_0.struct.housekeeper.request;

import com.qding.api.call.app.qding.v1_4_0.struct.housekeeper.BlueToothLog;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

import java.util.List;

/**
 * Created by qd on 2015/10/10.
 */
@Validate
public class AddBatchBlueToothLogRequest extends BaseRequest {

    @NotNullValidate
   private List<BlueToothLog> list;

    public List<BlueToothLog> getList() {
        return list;
    }

    public void setList(List<BlueToothLog> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "AddBlueToothLogRequest [list=" + list + ", "+super.toString() +"]";
    }
}
