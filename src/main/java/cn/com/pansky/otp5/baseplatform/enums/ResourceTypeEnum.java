package cn.com.pansky.otp5.baseplatform.enums;

/**
 * 
 * @ClassName ResourceTypeEnum
 * @Description 系统资源类型枚举
 * @author Administrator
 * @Date 2017年9月16日 下午5:28:42
 * @version 1.0.0
 */
public enum ResourceTypeEnum {  
//  RED, GREEN, BLANK, YELLOW  
    SYS("1","系统"), 页面("2","页面"),页面功能("3","页面功能");
    
    private String key;
    private String value;
    
    private ResourceTypeEnum(String key, String value) {
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
        for (ResourceTypeEnum c : ResourceTypeEnum.values()) {
            if (key.equals(c.key)) {
                return c.value;
            }
        }
        return null;
    }
    
} 