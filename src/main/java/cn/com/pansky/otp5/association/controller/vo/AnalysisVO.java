package cn.com.pansky.otp5.association.controller.vo;

import java.util.List;

import cn.com.pansky.otp5.baseplatform.controller.vo.BaseVO;

/**
 * 
 * @ClassName EnterpriseBasic
 * @Description 企业基本信息
 * @author Administrator
 * @Date 2017年9月21日 下午4:22:57
 * @version 1.0.0
 */
public class AnalysisVO{

    private List<String> data1;
    
    private List<String> data2;
    
    private List<AnalysisItem> list;

    
    public List<String> getData1() {
        return data1;
    }

    
    public void setData1(List<String> data1) {
        this.data1 = data1;
    }

    
    public List<String> getData2() {
        return data2;
    }

    
    public void setData2(List<String> data2) {
        this.data2 = data2;
    }


    
    public List<AnalysisItem> getList() {
        return list;
    }


    
    public void setList(List<AnalysisItem> list) {
        this.list = list;
    }
    
    
    
    
}
