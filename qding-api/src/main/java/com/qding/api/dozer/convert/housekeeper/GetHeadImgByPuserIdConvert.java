package com.qding.api.dozer.convert.housekeeper;

import com.qding.api.util.ApplicationContextUtil;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.manager.common.ModelResult;
import com.qding.manager.service.IPuserRPCService;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.MemberInfo;
import com.qding.passport.struct.request.GetMemberRequest;
import com.qding.passport.struct.response.GetMemberResponse;
import org.dozer.CustomConverter;

/**
 * Created by qd on 2016/6/29.
 */
public class GetHeadImgByPuserIdConvert implements CustomConverter {

    @Override
    public Object convert(Object existingDestinationFieldValue,
                          Object sourceFieldValue, Class<?> destinationClass,
                          Class<?> sourceClass) {

        IPuserRPCService puserRPCService =  ApplicationContextUtil.getBeansOfType(IPuserRPCService.class);
        IProfileService profileAPI =  ApplicationContextUtil.getBeansOfType(IProfileService.class);
        if (QDStringUtil.isNotNull(sourceFieldValue)) {
            ModelResult modelResult = puserRPCService.getMidByPuserId(String.valueOf(sourceFieldValue));
            if (HttpStatus.OK.getStatusCode() == modelResult.getCode()) {
                GetMemberRequest memberRequest = new GetMemberRequest();
                if (QDStringUtil.isNotNull(modelResult.getEntity())){
                    memberRequest.setMemberId(String.valueOf(modelResult.getEntity()));
                    GetMemberResponse memberResponse =  profileAPI.getMemberById(memberRequest);
                    if (HttpStatus.OK.getStatusCode() == memberResponse.getReturnInfo().getCode()) {
                        MemberInfo memberInfo = memberResponse.getMemberInfo();
                        return memberInfo.getHeadImg();
                    }
                }
            }
        }
        return "";
    }
}
