package cn.com.pansky.otp5.association.dao.po;

import java.util.Date;

import cn.com.pansky.otp5.baseplatform.dao.po.BasePO;

/**
 * 
 * @ClassName KnowledgeRight
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年9月27日 下午8:39:45
 * @version 1.0.0
 */
public class KnowledgeRight extends BasePO{

    private String id;
    //名称
    private String name;
    //企业ID
    private String eId;
    //受理号|登记号|专利号
    private String code;
    //
    private String type;
    //通过事件
    private Date passTime;
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

    
    public String getCode() {
        return code;
    }

    
    public void setCode(String code) {
        this.code = code;
    }

    
    public Date getPassTime() {
        return passTime;
    }

    
    public void setPassTime(Date passTime) {
        this.passTime = passTime;
    }

    public String getEnable() {
        return enable;
    }

    
    public void setEnable(String enable) {
        this.enable = enable;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    
    public String getType() {
        return type;
    }

    
    public void setType(String type) {
        this.type = type;
    }
    
    
    
}
