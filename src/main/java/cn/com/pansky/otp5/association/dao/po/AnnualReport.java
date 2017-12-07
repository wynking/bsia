package cn.com.pansky.otp5.association.dao.po;

import cn.com.pansky.otp5.baseplatform.dao.po.BasePO;

/**
 * 
 * @ClassName AnnualReport
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年9月26日 下午10:32:10
 * @version 1.0.0
 */
public class AnnualReport extends BasePO{

    private String id;
    //企业ID
    private String eId;
    //收入总额
    private Double totalIncome;
    //资产总额
    private Double totalAssets;
    //利润总额
    private Double totalProfit;
    //净利润
    private Double netProfit;
    //研发费用总额
    private Double developExpenses;
    //研发费用占收入比例
    private Double scale;
    //年度
    private String year;
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

    
    public Double getTotalIncome() {
        return totalIncome;
    }

    
    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    
    public Double getTotalAssets() {
        return totalAssets;
    }

    
    public void setTotalAssets(Double totalAssets) {
        this.totalAssets = totalAssets;
    }

    
    public Double getTotalProfit() {
        return totalProfit;
    }

    
    public void setTotalProfit(Double totalProfit) {
        this.totalProfit = totalProfit;
    }

    
    public Double getNetProfit() {
        return netProfit;
    }

    
    public void setNetProfit(Double netProfit) {
        this.netProfit = netProfit;
    }

    
    public Double getDevelopExpenses() {
        return developExpenses;
    }

    
    public void setDevelopExpenses(Double developExpenses) {
        this.developExpenses = developExpenses;
    }

    
    public Double getScale() {
        return scale;
    }

    
    public void setScale(Double scale) {
        this.scale = scale;
    }

    
    public String getYear() {
        return year;
    }

    
    public void setYear(String year) {
        this.year = year;
    }

    
    public String getEnable() {
        return enable;
    }

    
    public void setEnable(String enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "AnnualReport [id=" + id + ", eId=" + eId + ", totalIncome=" + totalIncome + ", totalAssets="
                + totalAssets + ", totalProfit=" + totalProfit + ", netProfit=" + netProfit + ", developExpenses="
                + developExpenses + ", scale=" + scale + ", year=" + year + ", enable=" + enable + "]";
    }
    
    
}
