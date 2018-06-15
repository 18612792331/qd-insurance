package com.qding.api.dozer.convert.user;

import org.dozer.CustomConverter;
import org.dozer.Mapper;

import com.qding.api.call.app.qding.v1_3_0.struct.user.Member;
import com.qding.api.util.ApplicationContextUtil;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.MemberInfo;
import com.qding.passport.struct.request.GetMemberRequest;
import com.qding.passport.struct.response.GetMemberResponse;

public class GetMemberByIdConvert implements CustomConverter {

	@Override
	public Object convert(Object existingDestinationFieldValue,
			Object sourceFieldValue, Class<?> destinationClass,
			Class<?> sourceClass) {
		
		String memberId = sourceFieldValue.toString();
		IProfileService profileAPI = ApplicationContextUtil.getBeansOfType(IProfileService.class);
		GetMemberRequest request = new GetMemberRequest ();
		request.setMemberId(memberId);
		GetMemberResponse response = profileAPI.getMemberById(request);
		MemberInfo memberInfo = response.getMemberInfo();
		
		if(memberInfo == null) {
			return null;
		}
		
		Mapper mapper = ApplicationContextUtil.getBeansOfType(Mapper.class);
		
		Member member = mapper.map(memberInfo, Member.class);
		
		return member;
	}

}
