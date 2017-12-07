package cn.com.pansky.otp5.association.controller.vo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import cn.com.pansky.otp5.baseplatform.controller.vo.BaseVO;
import cn.com.pansky.otp5.baseplatform.dao.po.Dictionary;
import cn.com.pansky.otp5.common.ConstantUtil;
import cn.com.pansky.otp5.common.DictionaryUtil;

/**
 * 
 * @ClassName EnterpriseBasicVO
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年9月25日 下午7:36:05
 * @version 1.0.0
 */
public class EnterpriseBasicVO extends BaseVO{

    private String id;
    
    //企业名称
    private String name;

    //企业网址
    private String url;

    //企业注册号
    private String code;

    //企业介绍
    private String remark;

    //是否上市
    private String isPublic;

    //是否高新
    private String isHigh;

    //高新通过事件
    private String passHighDate;

    //是否会员
    private String isMember;

    //信息来源
    private String infoSources;
    
    //信息来源name
    private String infoSourcesName;

    //初次入会时间
    private String firstTime;

    //会员级别
    private String hyLevel;
    
    //会员级别name
    private String hyLevelName;

    //注册资金
    private String registeredCapital;

    //员工规模
    private String employeeNum;

    //企业地址code（省市县）
    private String address;

    //企业地址name（省+市+县）
    private String addressName;

    //企业详细地址
    private String addressDetail;

    //邮政编码
    private String postalCode;

    //注册地址（省市县）
    private String registerAddress;

    //注册详细地址
    private String registerAddressDetail;
    
    //状态 0：停用 1：启用  2：删除
    private String enable;
    
    //当年（月平均）职工总数
    private String avgNum;
    
    //研发人员总数
    private String yfNum;
    
    //管理人员总数
    private String glNum;
    
    //大专及本科人数
    private String zbNum;
    
    //硕士学历人数
    private String ssNum;
    
    //博士学历人数
    private String bsNum;
    
    //经度
    private String longitude;
    
    //维度
    private String dimension;

    //技术领域
    private String[] techs;

    //行业领域
    private String[] industrys;

    //其他资质
    private String[] aptitudes;
    
    private Map<String,String> otherParams;
    
    //导出excel使用
    private String aptitude_names;
    
    //导出excel使用
    private String aptitude_passTimes;

    private String fzrName;

    private String fzrJob;

    private String fzrPhone;

    private String fzrTel;

    private String fzrEmail;

    private String lxrName;

    private String lxrJob;

    private String lxrPhone;

    private String lxrTel;

    private String lxrEmail;
    
    private String avgNum_start;
    
    private String avgNum_end;
    
    private String totalIncome_start;
    
    private String totalIncome_end;
    
    private String developExpenses_start;
    
    private String developExpenses_end;
    
    private String registeredCapital_start;
    
    private String registeredCapital_end;
    //归属人
    private String owner;
    
    public String getId() {
        return id;
    }

    
    public void setId(String id) {
        this.id = id;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    
    public String getUrl() {
        return url;
    }

    
    public void setUrl(String url) {
        this.url = url;
    }

    
    public String getCode() {
        return code;
    }

    
    public void setCode(String code) {
        this.code = code;
    }

    
    public String getRemark() {
        return remark;
    }

    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsPublic() {
        return isPublic;
    }

    
    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
    }

    
    public String getIsHigh() {
        return isHigh;
    }

    
    public void setIsHigh(String isHigh) {
        this.isHigh = isHigh;
    }

    
    public String getIsMember() {
        return isMember;
    }

    
    public void setIsMember(String isMember) {
        this.isMember = isMember;
    }

    
    public String getFirstTime() {
        return firstTime;
    }

    
    public void setFirstTime(String firstTime) {
        this.firstTime = firstTime;
    }

    
    public String getHyLevel() {
        return hyLevel;
    }

    
    public void setHyLevel(String hyLevel) {
        this.hyLevel = hyLevel;
    }

    
    public String getRegisteredCapital() {
        return registeredCapital;
    }

    
    public void setRegisteredCapital(String registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    
    public String getEmployeeNum() {
        return employeeNum;
    }

    
    public void setEmployeeNum(String employeeNum) {
        this.employeeNum = employeeNum;
    }

    
    public String getAddress() {
        return address;
    }

    
    public void setAddress(String address) {
        this.address = address;
    }

    
    public String getAddressDetail() {
        return addressDetail;
    }

    
    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    
    public String getPostalCode() {
        return postalCode;
    }

    
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    
    public String getRegisterAddress() {
        return registerAddress;
    }


    
    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }


    
    public String getRegisterAddressDetail() {
        return registerAddressDetail;
    }


    
    public void setRegisterAddressDetail(String registerAddressDetail) {
        this.registerAddressDetail = registerAddressDetail;
    }


    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    
    public String getInfoSources() {
        return infoSources;
    }

    public void setInfoSources(String infoSources) {
        this.infoSources = infoSources;
    }


    
    public String[] getTechs() {
        return techs;
    }


    
    public void setTechs(String[] techs) {
        this.techs = techs;
    }


    
    public String[] getIndustrys() {
        return industrys;
    }


    
    public void setIndustrys(String[] industrys) {
        this.industrys = industrys;
    }


    
    public String getAvgNum() {
        return avgNum;
    }


    
    public void setAvgNum(String avgNum) {
        this.avgNum = avgNum;
    }
    
    public String getYfNum() {
        return yfNum;
    }


    
    public void setYfNum(String yfNum) {
        this.yfNum = yfNum;
    }


    public String getGlNum() {
        return glNum;
    }


    
    public void setGlNum(String glNum) {
        this.glNum = glNum;
    }


    
    public String getZbNum() {
        return zbNum;
    }


    
    public void setZbNum(String zbNum) {
        this.zbNum = zbNum;
    }


    
    public String getSsNum() {
        return ssNum;
    }


    
    public void setSsNum(String ssNum) {
        this.ssNum = ssNum;
    }


    
    public String getBsNum() {
        return bsNum;
    }


    
    public void setBsNum(String bsNum) {
        this.bsNum = bsNum;
    }
    
    
    
    
    public String getInfoSourcesName() {
        return infoSourcesName;
    }


    
    public void setInfoSourcesName(String infoSourcesName) {
        this.infoSourcesName = infoSourcesName;
    }


    
    public String getHyLevelName() {
        return hyLevelName;
    }


    
    public void setHyLevelName(String hyLevelName) {
        this.hyLevelName = hyLevelName;
    }
    
    

    
    public String[] getAptitudes() {
        return aptitudes;
    }


    
    public void setAptitudes(String[] aptitudes) {
        this.aptitudes = aptitudes;
    }


    public void valToName(EnterpriseBasicVO vo,List<Dictionary> dics){
        String infoSourcesName = "";
        if(StringUtils.isNotEmpty(vo.getInfoSources())){
            infoSourcesName = DictionaryUtil.getNameByCode(vo.getInfoSources(), DictionaryUtil.getDicsByType(dics, ConstantUtil.DIC_INFO_SOURCES));
        }
        String hyLevelName = "";
        if(StringUtils.isNotEmpty(vo.getHyLevel())){
            hyLevelName = DictionaryUtil.getNameByCode(vo.getHyLevel(), DictionaryUtil.getDicsByType(dics, ConstantUtil.DIC_HY_LEVEL));
        }
        vo.setInfoSourcesName(infoSourcesName);
        vo.setHyLevelName(hyLevelName);
    }
    
    


    
    public Map<String, String> getOtherParams() {
        return otherParams;
    }


    
    public void setOtherParams(Map<String, String> otherParams) {
        this.otherParams = otherParams;
    }


    
    public String getPassHighDate() {
        return passHighDate;
    }


    
    public void setPassHighDate(String passHighDate) {
        this.passHighDate = passHighDate;
    }


    
    public String getLongitude() {
        return longitude;
    }


    
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }


    
    public String getDimension() {
        return dimension;
    }


    
    public void setDimension(String dimension) {
        this.dimension = dimension;
    }
    
    
    

    
    public String getAddressName() {
        return addressName;
    }


    
    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }


    
    public String getAptitude_names() {
        return aptitude_names;
    }


    
    public void setAptitude_names(String aptitude_names) {
        this.aptitude_names = aptitude_names;
    }


    
    public String getAptitude_passTimes() {
        return aptitude_passTimes;
    }


    
    public void setAptitude_passTimes(String aptitude_passTimes) {
        this.aptitude_passTimes = aptitude_passTimes;
    }


    
    public String getFzrName() {
        return fzrName;
    }


    
    public void setFzrName(String fzrName) {
        this.fzrName = fzrName;
    }


    
    public String getFzrJob() {
        return fzrJob;
    }


    
    public void setFzrJob(String fzrJob) {
        this.fzrJob = fzrJob;
    }


    
    public String getFzrPhone() {
        return fzrPhone;
    }


    
    public void setFzrPhone(String fzrPhone) {
        this.fzrPhone = fzrPhone;
    }


    
    public String getFzrTel() {
        return fzrTel;
    }


    
    public void setFzrTel(String fzrTel) {
        this.fzrTel = fzrTel;
    }


    
    public String getFzrEmail() {
        return fzrEmail;
    }


    
    public void setFzrEmail(String fzrEmail) {
        this.fzrEmail = fzrEmail;
    }


    
    public String getLxrName() {
        return lxrName;
    }


    
    public void setLxrName(String lxrName) {
        this.lxrName = lxrName;
    }


    
    public String getLxrJob() {
        return lxrJob;
    }


    
    public void setLxrJob(String lxrJob) {
        this.lxrJob = lxrJob;
    }


    
    public String getLxrPhone() {
        return lxrPhone;
    }


    
    public void setLxrPhone(String lxrPhone) {
        this.lxrPhone = lxrPhone;
    }


    
    public String getLxrTel() {
        return lxrTel;
    }


    
    public void setLxrTel(String lxrTel) {
        this.lxrTel = lxrTel;
    }


    
    public String getLxrEmail() {
        return lxrEmail;
    }


    
    public void setLxrEmail(String lxrEmail) {
        this.lxrEmail = lxrEmail;
    }


    
    public String getAvgNum_start() {
        return avgNum_start;
    }


    
    public void setAvgNum_start(String avgNum_start) {
        this.avgNum_start = avgNum_start;
    }


    
    public String getAvgNum_end() {
        return avgNum_end;
    }


    
    public void setAvgNum_end(String avgNum_end) {
        this.avgNum_end = avgNum_end;
    }


    
    public String getTotalIncome_start() {
        return totalIncome_start;
    }


    
    public void setTotalIncome_start(String totalIncome_start) {
        this.totalIncome_start = totalIncome_start;
    }


    
    public String getTotalIncome_end() {
        return totalIncome_end;
    }


    
    public void setTotalIncome_end(String totalIncome_end) {
        this.totalIncome_end = totalIncome_end;
    }


    
    public String getDevelopExpenses_start() {
        return developExpenses_start;
    }


    
    public void setDevelopExpenses_start(String developExpenses_start) {
        this.developExpenses_start = developExpenses_start;
    }


    
    public String getDevelopExpenses_end() {
        return developExpenses_end;
    }


    
    public void setDevelopExpenses_end(String developExpenses_end) {
        this.developExpenses_end = developExpenses_end;
    }


    
    public String getRegisteredCapital_start() {
        return registeredCapital_start;
    }


    
    public void setRegisteredCapital_start(String registeredCapital_start) {
        this.registeredCapital_start = registeredCapital_start;
    }


    
    public String getRegisteredCapital_end() {
        return registeredCapital_end;
    }


    
    public void setRegisteredCapital_end(String registeredCapital_end) {
        this.registeredCapital_end = registeredCapital_end;
    }

    
    public String getOwner() {
        return owner;
    }


    
    public void setOwner(String owner) {
        this.owner = owner;
    }



    @Override
    public String toString() {
        return "EnterpriseBasicVO [id=" + id + ", name=" + name + ", url=" + url + ", code=" + code + ", remark="
                + remark + ", isPublic=" + isPublic + ", isHigh=" + isHigh + ", passHighDate=" + passHighDate
                + ", isMember=" + isMember + ", infoSources=" + infoSources + ", infoSourcesName=" + infoSourcesName
                + ", firstTime=" + firstTime + ", hyLevel=" + hyLevel + ", hyLevelName=" + hyLevelName
                + ", registeredCapital=" + registeredCapital + ", employeeNum=" + employeeNum + ", address=" + address
                + ", addressName=" + addressName + ", addressDetail=" + addressDetail + ", postalCode=" + postalCode
                + ", registerAddress=" + registerAddress + ", registerAddressDetail=" + registerAddressDetail
                + ", enable=" + enable + ", avgNum=" + avgNum + ", yfNum=" + yfNum + ", glNum=" + glNum + ", zbNum="
                + zbNum + ", ssNum=" + ssNum + ", bsNum=" + bsNum + ", longitude=" + longitude + ", dimension="
                + dimension + ", techs=" + Arrays.toString(techs) + ", industrys=" + Arrays.toString(industrys)
                + ", aptitudes=" + Arrays.toString(aptitudes) + ", otherParams=" + otherParams + ", aptitude_names="
                + aptitude_names + ", aptitude_passTimes=" + aptitude_passTimes + ", fzrName=" + fzrName + ", fzrJob="
                + fzrJob + ", fzrPhone=" + fzrPhone + ", fzrTel=" + fzrTel + ", fzrEmail=" + fzrEmail + ", lxrName="
                + lxrName + ", lxrJob=" + lxrJob + ", lxrPhone=" + lxrPhone + ", lxrTel=" + lxrTel + ", lxrEmail="
                + lxrEmail + ", avgNum_start=" + avgNum_start + ", avgNum_end=" + avgNum_end + ", totalIncome_start="
                + totalIncome_start + ", totalIncome_end=" + totalIncome_end + ", developExpenses_start="
                + developExpenses_start + ", developExpenses_end=" + developExpenses_end + ", registeredCapital_start="
                + registeredCapital_start + ", registeredCapital_end=" + registeredCapital_end + "]";
    }

    
    
    
}
