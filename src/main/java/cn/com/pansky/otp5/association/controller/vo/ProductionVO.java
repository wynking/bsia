package cn.com.pansky.otp5.association.controller.vo;

import java.util.Arrays;
import java.util.List;

import cn.com.pansky.otp5.association.dao.po.ProductionFieldRelate;
import cn.com.pansky.otp5.baseplatform.controller.vo.BaseVO;

/**
 * 
 * @ClassName ProductionVO
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年9月28日 下午9:52:59
 * @version 1.0.0
 */
public class ProductionVO extends BaseVO{

    private String id;
    //企业ID
    private String eId;
    //受理号
    private String income;
    //登记号
    private String name;
    //状态
    private String enable;
    //服务领域code
    private String[] fwlys;
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
    
    public String getIncome() {
        return income;
    }
    
    public void setIncome(String income) {
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

    
    public String[] getFwlys() {
        return fwlys;
    }

    
    public void setFwlys(String[] fwlys) {
        this.fwlys = fwlys;
    }

    
    public String getFwlyNames() {
        return fwlyNames;
    }

    
    public void setFwlyNames(String fwlyNames) {
        this.fwlyNames = fwlyNames;
    }


  
    
}
