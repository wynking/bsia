package cn.com.pansky.otp5.baseplatform.controller.vo;

/**
 * 
 * @ClassName UserVO
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年9月8日 下午5:54:58
 * @version 1.0.0
 */
public class UserVO extends BaseVO {

    private String id;
    
    private String loginName;
    
    private String trueName;
    
    private String password;
    
    private String confirmPassword;
    
    private String phone;

    private String isSuper;
    
    private String enabled;
    
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

    
    public String getIsSuper() {
        return isSuper;
    }

    
    public void setIsSuper(String isSuper) {
        this.isSuper = isSuper;
    }

    
    public String getConfirmPassword() {
        return confirmPassword;
    }

    
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    
    public String getEnabled() {
        return enabled;
    }

    
    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    
    
    
}
