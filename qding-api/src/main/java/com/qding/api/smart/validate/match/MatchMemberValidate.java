package com.qding.api.smart.validate.match;

import com.qding.api.call.app.qding.v1_3_0.struct.user.Member;
import com.qding.api.smart.validate.AccountMemberValidate;
import com.qding.api.smart.validate.rule.MemberValidate;
import com.qding.api.util.ApplicationContextUtil;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.smart.validate.match.AbstractMatchValidate;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.MemberInfo;
import com.qding.passport.struct.request.GetMemberRequest;
import com.qding.passport.struct.response.GetMemberResponse;

/**
 * 验证会员是否可用
 * @author lichao
 *
 */
public class MatchMemberValidate extends AbstractMatchValidate<MemberValidate>{

	@Override
	public boolean validate(MemberValidate t, String fieldName, Object value)
			throws ServiceException {

		if(value == null || value.toString().trim().length() == 0) {
			
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "缺少memberId");

		}
		
		IProfileService profileService = ApplicationContextUtil.getBeansOfType(IProfileService.class);
		
		GetMemberRequest request = new GetMemberRequest();
		
		request.setMemberId(value.toString());
		
		GetMemberResponse response = profileService.getMemberById(request);
		
		if(HttpStatus.OK.getStatusCode() == response.getReturnInfo().getCode()) {
			
			MemberInfo memberInfo = response.getMemberInfo();
			Member member = new Member();
			member.setMemberStatus(member.getMemberStatus());
			return AccountMemberValidate.validateMember(member);

		} else {
			
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), response.getReturnInfo().getMessage());

		}
	}

}
