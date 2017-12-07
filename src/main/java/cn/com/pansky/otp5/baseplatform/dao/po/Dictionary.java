package cn.com.pansky.otp5.baseplatform.dao.po;

/**
 * 
 * @ClassName Dictionary
 * @Description 字典信息表
 * @author Administrator
 * @Date 2017年9月21日 下午5:10:26
 * @version 1.0.0
 */
public class Dictionary extends BasePO{
    
    private String id;
    private String code;
    private String name;
    private String type;
    private String pCode;
    private String enable;
    private String isDicName;
    private Integer level;
    private String pId;//该字段数据库中没有，主要用于接收前端数据
	
	
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getpCode() {
        return pCode;
    }
    
    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public String getIsDicName() {
        return isDicName;
    }

    
    public void setIsDicName(String isDicName) {
        this.isDicName = isDicName;
    }

    
    public void setEnable(String enable) {
        this.enable = enable;
    }

    
    public String getEnable() {
        return enable;
    }

    
    public Integer getLevel() {
        return level;
    }

    
    public void setLevel(Integer level) {
        this.level = level;
    }

    
    public String getpId() {
        return pId;
    }

    
    public void setpId(String pId) {
        this.pId = pId;
    }

    @Override
    public String toString() {
        return "Dictionary [id=" + id + ", code=" + code + ", name=" + name + ", type=" + type + ", pCode=" + pCode
                + ", enable=" + enable + ", isDicName=" + isDicName + ", level=" + level + ", pId=" + pId + "]";
    }

	
	

}