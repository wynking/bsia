package cn.com.pansky.otp5.baseplatform.dao.po;

public class RoleResource extends BasePO{
	
    private static final long serialVersionUID = 1L;

    private String id;
	
	private String roleId;

    private String resourceId;

    
    public String getId() {
        return id;
    }

    
    public void setId(String id) {
        this.id = id;
    }

    
    public String getRoleId() {
        return roleId;
    }

    
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    
    public String getResourceId() {
        return resourceId;
    }

    
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    

}