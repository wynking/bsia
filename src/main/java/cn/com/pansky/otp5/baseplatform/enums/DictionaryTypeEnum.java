package cn.com.pansky.otp5.baseplatform.enums;

/**
 * 
 * @ClassName ResourceTypeEnum
 * @Description 系统资源类型枚举
 * @author Administrator
 * @Date 2017年9月16日 下午5:28:42
 * @version 1.0.0
 */
public enum DictionaryTypeEnum {  
    
    MUMBER_LEVEL("hyjb","会员级别"),
    TECH_FILED("jsly","技术领域"),
    INFO_SOURCES("xxly","信息来源"),
    ZW("zw","职务"),
    FWLY("fwly","服务领域"),
    YN("yn","是否"),
    QTZZ("qtzz","其他资质");
    
    private String key;
    private String value;
    
    private DictionaryTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }
    
    public String getKey() {
        return key;
    }
  
    public void setKey(String key) {
        this.key = key;
    }
 
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }



    public static String getValue(String key) {
        for (DictionaryTypeEnum c : DictionaryTypeEnum.values()) {
            if (key.equals(c.key)) {
                return c.value;
            }
        }
        return null;
    }
    
} 