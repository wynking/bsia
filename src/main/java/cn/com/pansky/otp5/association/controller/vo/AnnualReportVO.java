package cn.com.pansky.otp5.association.controller.vo;

import cn.com.pansky.otp5.baseplatform.controller.vo.BaseVO;

/**
 * 
 * @ClassName AnnualReportVO
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年9月27日 上午10:00:20
 * @version 1.0.0
 */
public class AnnualReportVO extends BaseVO{

    private String id;
    //企业ID
    private String eId;
    //收入总额
    private String totalIncome;
    //资产总额
    private String totalAssets;
    //利润总额
    private String totalProfit;
    //净利润
    private String netProfit;
    //研发费用总额
    private String developExpenses;
    //研发费用占收入比例
    private String scale;
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
    
    public String getTotalIncome() {
        return totalIncome;
    }
    
    public void setTotalIncome(String totalIncome) {
        this.totalIncome = totalIncome;
    }
    
    public String getTotalAssets() {
        return totalAssets;
    }
    
    public void setTotalAssets(String totalAssets) {
        this.totalAssets = totalAssets;
    }
    
    public String getTotalProfit() {
        return totalProfit;
    }
    
    public void setTotalProfit(String totalProfit) {
        this.totalProfit = totalProfit;
    }
    
    public String getNetProfit() {
        return netProfit;
    }
    
    public void setNetProfit(String netProfit) {
        this.netProfit = netProfit;
    }
    
    public String getDevelopExpenses() {
        return developExpenses;
    }
    
    public void setDevelopExpenses(String developExpenses) {
        this.developExpenses = developExpenses;
    }
    
    public String getScale() {
        return scale;
    }
    
    public void setScale(String scale) {
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
        return "AnnualReportVO [id=" + id + ", eId=" + eId + ", totalIncome=" + totalIncome + ", totalAssets="
                + totalAssets + ", totalProfit=" + totalProfit + ", netProfit=" + netProfit + ", developExpenses="
                + developExpenses + ", scale=" + scale + ", year=" + year + ", enable=" + enable + "]";
    }
    
    
    
}
