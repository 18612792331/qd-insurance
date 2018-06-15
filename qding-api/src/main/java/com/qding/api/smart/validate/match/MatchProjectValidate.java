package com.qding.api.smart.validate.match;

import com.qding.api.smart.validate.rule.ProjectValidate;
import com.qding.api.util.ApplicationContextUtil;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.smart.validate.match.AbstractMatchValidate;

/**
 * 验证项目ID
 * @author lichao
 *
 */
public class MatchProjectValidate extends AbstractMatchValidate<ProjectValidate>{

	@Override
	public boolean validate(ProjectValidate t, String fieldName, Object value)
			throws ServiceException {
		
		if(value == null || value.toString().trim().length() == 0) {
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "缺少projectId");

		}
		
		ProjectReadRemote projectReadRemote = ApplicationContextUtil.getBeansOfType(ProjectReadRemote.class);
		
		Project project = projectReadRemote.get(Long.parseLong(value.toString()));
		
		if(project == null) {
			
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "项目不存在");
			
		} 
		
		return true;
	}
}
