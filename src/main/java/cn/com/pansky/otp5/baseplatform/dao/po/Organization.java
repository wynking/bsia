package cn.com.pansky.otp5.baseplatform.dao.po;

public class Organization extends BasePO{
	
	private String id;
	
	private String name;

    private String code;

    private String remark;

    private String pId;

    private String enabled;

    
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

    
    public String getCode() {
        return code;
    }

    
    public void setCode(String code) {
        this.code = code;
    }

    
    public String getRemark() {
        return remark;
    }

    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    
    public String getpId() {
        return pId;
    }

    
    public void setpId(String pId) {
        this.pId = pId;
    }

    
    public String getEnabled() {
        return enabled;
    }

    
    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

}