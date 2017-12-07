package cn.com.pansky.otp5.baseplatform.controller.vo;

/**
 * 
 * @ClassName ResourceVO
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author wyn
 * @Date 2017年9月11日 下午11:59:34
 * @version 1.0.0
 */
public class ResourceVO extends BaseVO{
	
	private String id;
	
	private String name;

    private String url;

    private String type;

    private String pId;

    private String enabled;

    
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

    
    public String getType() {
        return type;
    }

    
    public void setType(String type) {
        this.type = type;
    }

    
    public String getpId() {
        return pId;
    }

    
    public void setpId(String pId) {
        this.pId = pId;
    }

    
    public String getEnabled() {
        return enabled;
    }

    
    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }


    @Override
    public String toString() {
        return "ResourceVO [id=" + id + ", name=" + name + ", url=" + url + ", type=" + type + ", pId=" + pId
                + ", enabled=" + enabled + "]";
    }
    
    
	
	

}