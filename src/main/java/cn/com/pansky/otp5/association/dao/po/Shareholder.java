package cn.com.pansky.otp5.association.dao.po;

import cn.com.pansky.otp5.baseplatform.dao.po.BasePO;

/**
 * 
 * @ClassName Shareholder
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年9月27日 下午4:31:00
 * @version 1.0.0
 */
public class Shareholder extends BasePO{

    private String id;
    //企业ID
    private String eId;
    //股东名称
    private String name;
    //持股数量
    private Integer quantity;
    //持股比例
    private Double ratio;
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
    
    public String getEnable() {
        return enable;
    }
    
    public void setEnable(String enable) {
        this.enable = enable;
    }

    
    public Integer getQuantity() {
        return quantity;
    }

    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    
    public Double getRatio() {
        return ratio;
    }

    
    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }
    
    
    
}
