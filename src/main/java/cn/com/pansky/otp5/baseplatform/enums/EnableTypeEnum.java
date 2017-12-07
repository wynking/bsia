package cn.com.pansky.otp5.baseplatform.enums;

/**
 * 
 * @ClassName ResourceTypeEnum
 * @Description 系统资源类型枚举
 * @author Administrator
 * @Date 2017年9月16日 下午5:28:42
 * @version 1.0.0
 */
public enum EnableTypeEnum {  
//  RED, GREEN, BLANK, YELLOW  
    TY("0","停用"), QY("1","启用"),SC("2","删除");
    
    private String key;
    private String value;
    
    private EnableTypeEnum(String key, String value) {
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
        for (EnableTypeEnum c : EnableTypeEnum.values()) {
            if (key.equals(c.key)) {
                return c.value;
            }
        }
        return null;
    }
    
} 