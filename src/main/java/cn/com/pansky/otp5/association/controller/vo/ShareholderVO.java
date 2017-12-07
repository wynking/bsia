package cn.com.pansky.otp5.association.controller.vo;

import cn.com.pansky.otp5.baseplatform.controller.vo.BaseVO;

/**
 * 
 * @ClassName ShareholderVO
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年9月27日 下午6:31:21
 * @version 1.0.0
 */
public class ShareholderVO extends BaseVO{

    private String id;
    //企业ID
    private String eId;
    //股东名称
    private String name;
    //持股数量
    private String quantity;
    //持股比例
    private String ratio;
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

    
    public String getQuantity() {
        return quantity;
    }

    
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    
    public String getRatio() {
        return ratio;
    }

    
    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    @Override
    public String toString() {
        return "ShareholderVO [id=" + id + ", eId=" + eId + ", name=" + name + ", quantity=" + quantity + ", ratio="
                + ratio + ", enable=" + enable + "]";
    }
    
    
    
}
