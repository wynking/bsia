package cn.com.pansky.otp5.association.controller.vo;

import cn.com.pansky.otp5.baseplatform.controller.vo.BaseVO;

/**
 * 
 * @ClassName EnterpriseBasic
 * @Description 企业基本信息
 * @author Administrator
 * @Date 2017年9月21日 下午4:22:57
 * @version 1.0.0
 */
public class ContactInfoVO extends BaseVO{

    private String id;
    
    //企业ID
    private String eId;

    //企业网址
    private String name;

    //职务Code
    private String job;
    
    //职务名称
    private String jobName;

    //固话1
    private String tel_1;
    
    //固话2
    private String tel_2;
    
    //手机
    private String phone;
    
    //邮箱
    private String email;
    
    //状态
    private String enable;
    
    
    public String getId() {
        return id;
    }

    
    public void setId(String id) {
        this.id = id;
    }

    
    public String geteId() {
        return eId;
    }

    
    public void seteId(String eId) {
        this.eId = eId;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    
    public String getJob() {
        return job;
    }

    
    public void setJob(String job) {
        this.job = job;
    }

    
    public String getTel_1() {
        return tel_1;
    }

    
    public void setTel_1(String tel_1) {
        this.tel_1 = tel_1;
    }

    
    public String getTel_2() {
        return tel_2;
    }

    
    public void setTel_2(String tel_2) {
        this.tel_2 = tel_2;
    }

    
    public String getPhone() {
        return phone;
    }

    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    public String getEmail() {
        return email;
    }

    
    public void setEmail(String email) {
        this.email = email;
    }

    
    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getJobName() {
        return jobName;
    }

    
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public String toString() {
        return "ContactInfoVO [id=" + id + ", eId=" + eId + ", name=" + name + ", job=" + job + ", jobName=" + jobName
                + ", tel_1=" + tel_1 + ", tel_2=" + tel_2 + ", phone=" + phone + ", email=" + email + ", enable="
                + enable + "]";
    }


}
