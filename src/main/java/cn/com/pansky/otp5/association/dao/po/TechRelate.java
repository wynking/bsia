package cn.com.pansky.otp5.association.dao.po;

import cn.com.pansky.otp5.baseplatform.dao.po.BasePO;

/**
 * 
 * @ClassName TechRelate
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年9月24日 下午5:58:54
 * @version 1.0.0
 */
public class TechRelate extends BasePO{

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
