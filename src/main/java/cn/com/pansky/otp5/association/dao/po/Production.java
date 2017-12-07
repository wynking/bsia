package cn.com.pansky.otp5.association.dao.po;

import java.util.List;

import cn.com.pansky.otp5.baseplatform.dao.po.BasePO;

/**
 * 
 * @ClassName Production
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年9月28日 上午11:58:57
 * @version 1.0.0
 */
public class Production extends BasePO{

    private String id;
    //企业ID
    private String eId;
    //受理号
    private double income;
    //登记号
    private String name;
    //状态
    private String enable;
    //服务领域
    private List<ProductionFieldRelate> fwlypos;
    //
    private String fwlyNames;
    
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
    
    public double getIncome() {
        return income;
    }
    
    public void setIncome(double income) {
        this.income = income;
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

    
    public List<ProductionFieldRelate> getFwlypos() {
        return fwlypos;
    }

    
    public void setFwlypos(List<ProductionFieldRelate> fwlypos) {
        this.fwlypos = fwlypos;
    }

    
    public String getFwlyNames() {
        return fwlyNames;
    }

    
    public void setFwlyNames(String fwlyNames) {
        this.fwlyNames = fwlyNames;
    }

    
    
    
}
