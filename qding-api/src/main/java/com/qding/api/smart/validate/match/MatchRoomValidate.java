package com.qding.api.smart.validate.match;

import com.qding.api.smart.validate.rule.AccountValidate;
import com.qding.api.smart.validate.rule.RoomValidate;
import com.qding.api.util.ApplicationContextUtil;
import com.qding.brick.pojo.biz.Room;
import com.qding.brick.remote.biz.RoomReadRemote;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.smart.validate.match.AbstractMatchValidate;

/**
 * 验证房间ID
 * @author lichao
 *
 */
public class MatchRoomValidate extends AbstractMatchValidate<RoomValidate>{

	@Override
	public boolean validate(RoomValidate t, String fieldName, Object value)
			throws ServiceException {
		
		if(value == null || value.toString().trim().length() == 0) {
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "缺少roomId");

		}
		
		RoomReadRemote roomReadRemote = ApplicationContextUtil.getBeansOfType(RoomReadRemote.class);
		
		Room room = roomReadRemote.get(Long.parseLong(value.toString()));
		
		if(room == null) {
			
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "房间不存在");
			
		} 
		
		return true;
	}
}
