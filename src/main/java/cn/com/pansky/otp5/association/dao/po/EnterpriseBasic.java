package cn.com.pansky.otp5.association.dao.po;

import java.util.Date;

import cn.com.pansky.otp5.baseplatform.dao.po.BasePO;

/**
 * 
 * @ClassName EnterpriseBasic
 * @Description 企业基本信息
 * @author Administrator
 * @Date 2017年9月21日 下午4:22:57
 * @version 1.0.0
 */
public class EnterpriseBasic extends BasePO{

    private String id;
    
    //企业名称
    private String name;

    //企业网址
    private String url;

    //供上注册号
    private String code;

    //企业介绍
    private String remark;

    //是否上市
    private String isPublic;

    //是否高新
    private String isHigh;
    
    //高新通过时间
    private Date passHighDate;

    //是否会员
    private String isMember;
    
    //信息来源
    private String infoSources;

    //初次入会时间
    private Date firstTime;

    //会员级别
    private String hyLevel;

    //注册资金
    private Double registeredCapital;

    //员工规模
    private String employeeNum;

    //企业通讯地址（省市县）
    private String address;

    //企业通讯详细地址
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
    private Integer avgNum;
    
    //研发人员总数
    private Integer yfNum;
    
    //管理人员总数
    private Integer glNum;
    
    //大专及本科人数
    private Integer zbNum;
    
    //硕士学历人数
    private Integer ssNum;
    
    //博士学历人数
    private Integer bsNum;
    
    //经度
    private String longitude;
    
    //维度
    private String dimension;

    //技术领域
    private String[] techs;

    //行业领域
    private String[] industrys;

    private String aptitudeNames;

    private String techNames;

    private String industryNames;

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
    //收入总额
    private Double totalIncome;
    //研发费用总额
    private Double developExpenses;
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

    
    
    public Date getPassHighDate() {
        return passHighDate;
    }


    
    public void setPassHighDate(Date passHighDate) {
        this.passHighDate = passHighDate;
    }


    public String getIsMember() {
        return isMember;
    }

    
    public void setIsMember(String isMember) {
        this.isMember = isMember;
    }

    
    public Date getFirstTime() {
        return firstTime;
    }

    
    public void setFirstTime(Date firstTime) {
        this.firstTime = firstTime;
    }

    
    public String getHyLevel() {
        return hyLevel;
    }

    
    public void setHyLevel(String hyLevel) {
        this.hyLevel = hyLevel;
    }

    
    public Double getRegisteredCapital() {
        return registeredCapital;
    }

    
    public void setRegisteredCapital(Double registeredCapital) {
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


    
    
    public Integer getAvgNum() {
        return avgNum;
    }


    
    public void setAvgNum(Integer avgNum) {
        this.avgNum = avgNum;
    }


    
    public Integer getYfNum() {
        return yfNum;
    }


    
    public void setYfNum(Integer yfNum) {
        this.yfNum = yfNum;
    }


    
    public Integer getGlNum() {
        return glNum;
    }


    
    public void setGlNum(Integer glNum) {
        this.glNum = glNum;
    }


    
    public Integer getZbNum() {
        return zbNum;
    }


    
    public void setZbNum(Integer zbNum) {
        this.zbNum = zbNum;
    }


    
    public Integer getSsNum() {
        return ssNum;
    }


    
    public void setSsNum(Integer ssNum) {
        this.ssNum = ssNum;
    }


    
    public Integer getBsNum() {
        return bsNum;
    }


    
    public void setBsNum(Integer bsNum) {
        this.bsNum = bsNum;
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
    
    
    public String getAptitudeNames() {
        return aptitudeNames;
    }

    public void setAptitudeNames(String aptitudeNames) {
        this.aptitudeNames = aptitudeNames;
    }


    
    public String getTechNames() {
        return techNames;
    }


    
    public void setTechNames(String techNames) {
        this.techNames = techNames;
    }


    
    public String getIndustryNames() {
        return industryNames;
    }


    
    public void setIndustryNames(String industryNames) {
        this.industryNames = industryNames;
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


    
    public Double getTotalIncome() {
        return totalIncome;
    }


    
    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }


    
    public Double getDevelopExpenses() {
        return developExpenses;
    }


    
    public void setDevelopExpenses(Double developExpenses) {
        this.developExpenses = developExpenses;
    }


    
    public String getOwner() {
        return owner;
    }


    
    public void setOwner(String owner) {
        this.owner = owner;
    }

    
    
}
