package cn.com.pansky.otp5.baseplatform.controller.vo;

import java.util.List;

import cn.com.pansky.otp5.baseplatform.dao.po.RoleResource;

/**
 * 
 * @ClassName RoleVO
 * @Description 角色VO
 * @author wyn
 * @Date 2017年12月1日 下午7:54:08
 * @version 1.0.0
 */
public class RoleVO extends BaseVO{
	
	private String id;
	
	private String name;

    private String remark;
    
    private String enabled;
    
    private String resourceIds;
    
    private List<RoleResource> rrs;

    
    public String getId() {
        return id;
    }

    
    public void setId(String id) {
        this.id = id;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    
    public String getRemark() {
        return remark;
    }

    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    
    public String getEnabled() {
        return enabled;
    }

    
    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }


    
    public String getResourceIds() {
        return resourceIds;
    }


    
    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }


    
    public List<RoleResource> getRrs() {
        return rrs;
    }


    
    public void setRrs(List<RoleResource> rrs) {
        this.rrs = rrs;
    }

}