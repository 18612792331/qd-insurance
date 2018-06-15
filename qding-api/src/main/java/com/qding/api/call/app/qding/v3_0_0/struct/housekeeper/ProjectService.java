package com.qding.api.call.app.qding.v3_0_0.struct.housekeeper;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2017/2/25.
 */
public class ProjectService extends SkipUrl implements Serializable {

    private static final long serialVersionUID = 6537154887174566013L;

    @ExplainAnnotation(explain = "业态服务名称")
    private String name;

    @ExplainAnnotation(explain = "业态服务图标")
    private String imageUrl;

    @ExplainAnnotation(explain = "业态服务类型名称")
    private String serviceTypeName;

    @ExplainAnnotation(explain = "业态服务类型")
    private String serviceType;

    @ExplainAnnotation(explain = "业态服务描述")
    private String serviceDesc;

    @ExplainAnnotation(explain = "业态服务ID")
    private String serviceId;
    
    @ExplainAnnotation(explain = "可查看权限类型,0登录，1绑定房屋",desc="3.2新增")
    private Integer permType;
    
    @ExplainAnnotation(explain = "绑定房屋身份，业主，家庭成员，租客",desc="3.2新增")
    private List<String> bindRoomRole;
    
    @ExplainAnnotation(explain = "标签名称",desc="3.2新增")
    private String tagName;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getServiceTypeName() {
        return serviceTypeName;
    }

    public void setServiceTypeName(String serviceTypeName) {
        this.serviceTypeName = serviceTypeName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

	public Integer getPermType() {
		return permType;
	}

	public void setPermType(Integer permType) {
		this.permType = permType;
	}

	public List<String> getBindRoomRole() {
		return bindRoomRole;
	}

	public void setBindRoomRole(List<String> bindRoomRole) {
		this.bindRoomRole = bindRoomRole;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

    
    
}
