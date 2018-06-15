package com.qding.api.smart.validate;

import com.qding.api.call.app.qding.v1_3_0.struct.user.Account;
import com.qding.api.call.app.qding.v1_3_0.struct.user.Member;
import com.qding.api.constant.Constant;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.passport.struct.MemberInfo;

/**
 * 验证用户是否可用
 * @author lichao
 *
 */
public class AccountMemberValidate {

	public static boolean validateMember(Member member) throws ServiceException {
		
		if(member == null) {
			
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "会员不存在");

		}
		
		if(QDStringUtil.isNotNull(member) && QDStringUtil.isNotNull(member.getMemberStatus())
				&& Constant.MEMBER_STATUS_ENABLE != Integer.parseInt(member.getMemberStatus())) {
				
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "你的会员账号已被冻结");
		}
			
		return true;
	}
	
	public static boolean validateAccount( Account user) throws ServiceException {
		
		if(user == null) {
			
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "账号不存在");

		}
		
		if(Constant.MEMBER_STATUS_ENABLE != user.getStatus()) {
			
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "你的账号已被冻结");
		}

		return true;
		
	}
}
