package com.qding.api.dozer.convert.user;
import java.util.List;
import java.util.Map;

import com.qding.member.model.MemberRoom;
import com.qding.member.rpc.IMemberRPC;
import com.qding.member.rpc.response.member.GetMemberInfoResponse;
import org.dozer.CustomConverter;

import com.qding.api.util.ApplicationContextUtil;
import com.qding.api.util.QDMemberRemote;

public class GetMemberProjectIdByRoomMemberIdConvert implements CustomConverter {

	@Override
	public Object convert(Object existingDestinationFieldValue,
			Object sourceFieldValue, Class<?> destinationClass,
			Class<?> sourceClass) {
		
		try {
			String memberId = sourceFieldValue.toString();
			IMemberRPC memberServiceAPI = ApplicationContextUtil.getBeansOfType(IMemberRPC.class);
			GetMemberInfoResponse memberInfo = memberServiceAPI.getMemberInfo(memberId);
			String projectId = memberInfo.getProjectId()!=null?String.valueOf(memberInfo.getProjectId()):"";
			return projectId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
