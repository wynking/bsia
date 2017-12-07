package cn.com.pansky.otp5.association.dao.po;

import cn.com.pansky.otp5.baseplatform.dao.po.BasePO;

/**
 * 
 * @ClassName ProductionFieldRelate
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年9月28日 下午4:05:55
 * @version 1.0.0
 */
public class ProductionFieldRelate extends BasePO{

    private String id;
    //产品ID
    private String productionId;
    //服务领域编码
    private String fwlyCode;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getProductionId() {
        return productionId;
    }

    
    public void setProductionId(String productionId) {
        this.productionId = productionId;
    }

    
    public String getFwlyCode() {
        return fwlyCode;
    }

    
    public void setFwlyCode(String fwlyCode) {
        this.fwlyCode = fwlyCode;
    }

    @Override
    public String toString() {
        return "ProductionFieldRelate [id=" + id + ", productionId=" + productionId + ", fwlyCode=" + fwlyCode + "]";
    }
    
    
}
