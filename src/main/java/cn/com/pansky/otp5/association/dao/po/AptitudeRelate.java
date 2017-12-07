package cn.com.pansky.otp5.association.dao.po;

import java.util.Date;

import cn.com.pansky.otp5.baseplatform.dao.po.BasePO;

/**
 * 
 * @ClassName AptitudeRelate
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年10月15日 上午10:50:31
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class AptitudeRelate extends BasePO{

    private String id;
    
    private String eId;
    
    private String code;
    
    private Date passTime;

    
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
    
    

}
