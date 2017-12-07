package cn.com.pansky.otp5.association.dao.po;

import cn.com.pansky.otp5.baseplatform.dao.po.BasePO;

/**
 * 
 * @ClassName IndustryRelate
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年10月15日 下午12:26:01
 * @version 1.0.0
 */
public class IndustryRelate extends BasePO{

    private String id;
    
    private String eId;
    
    private String code;

    
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


}
