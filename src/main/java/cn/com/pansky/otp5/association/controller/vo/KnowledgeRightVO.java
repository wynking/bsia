package cn.com.pansky.otp5.association.controller.vo;

import cn.com.pansky.otp5.baseplatform.controller.vo.BaseVO;

/**
 * 
 * @ClassName KnowledgeRightVO
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年9月27日 下午9:05:26
 * @version 1.0.0
 */
public class KnowledgeRightVO extends BaseVO{

    private String id;
    //企业ID
    private String eId;
    //名称
    private String name;
    //受理号|登记号|专利号
    private String code;
    //通过事件
    private String passTime;
    //知识产权类型
    private String type;
    //知识产权类型Name
    private String typeName;
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

    public String getCode() {
        return code;
    }

    
    public void setCode(String code) {
        this.code = code;
    }

    
    public String getPassTime() {
        return passTime;
    }

    
    public void setPassTime(String passTime) {
        this.passTime = passTime;
    }

    public String getEnable() {
        return enable;
    }

    
    public void setEnable(String enable) {
        this.enable = enable;
    }

    
    public String getType() {
        return type;
    }

    
    public void setType(String type) {
        this.type = type;
    }

    
    public String getTypeName() {
        return typeName;
    }

    
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    
    
    
}
