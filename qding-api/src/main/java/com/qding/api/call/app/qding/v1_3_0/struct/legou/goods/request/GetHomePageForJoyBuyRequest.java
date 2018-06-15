package com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 获取乐购首页信息
 * @author jiawenzheng
 *
 */
@Validate
public class GetHomePageForJoyBuyRequest extends BaseRequest{

	
	private static final long serialVersionUID = 4338701223764284937L;
	
	/**
     * 社区ID
     */
	@NotNullValidate
	private Long projectId;
	
	/**
	 * 获取点赞神数
	 */
	private boolean findClickLike = true;
	
	public boolean getFindClickLike() {
		return findClickLike;
	}

	public void setFindClickLike(boolean findClickLike) {
		this.findClickLike = findClickLike;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public GetHomePageForJoyBuyRequest () {
		
	}
	
	@Override
    public String toString() {
        return "GetHomePageForJoyBuyRequest [projectId="+projectId+
                 ",  findClickLike ="+findClickLike+" , toString()=" + super.toString() + "]";
    }

}
