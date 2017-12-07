package cn.com.pansky.otp5.baseplatform.dao.po;

import java.util.Date;

public class User extends BasePO{
	
	private String id;
	private String loginName;
	private String trueName;
	private String password;
	private String phone;
	private Date birthDate;
	private String userCode;
	private String enabled;
	private String isSuper;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    public String getLoginName() {
        return loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
    
    public String getIsSuper() {
        return isSuper;
    }
    
    public void setIsSuper(String isSuper) {
        this.isSuper = isSuper;
    }
	
	
	

}