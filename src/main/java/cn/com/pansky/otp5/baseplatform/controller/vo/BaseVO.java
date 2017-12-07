package cn.com.pansky.otp5.baseplatform.controller.vo;

/**
 * 
 * @ClassName BaseVO
 * @Description 所有VO对象的基类
 * @author wyn
 * @Date 2017年9月11日 下午11:59:22
 * @version 1.0.0
 */
public class BaseVO {

	/*
	 * 创建人
	 */
	private String creationUser;
	/*
	 * 创建时间
	 */
	private String creationTime;
	/*
	 * 最后修改人
	 */
	private String lastModifyUser;
	/*
	 * 最后修改时间
	 */
	private String lastModifyTime;
    
    public String getCreationUser() {
        return creationUser;
    }
    
    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }
    
    public String getCreationTime() {
        return creationTime;
    }
    
    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }
    
    public String getLastModifyUser() {
        return lastModifyUser;
    }
    
    public void setLastModifyUser(String lastModifyUser) {
        this.lastModifyUser = lastModifyUser;
    }
    
    public String getLastModifyTime() {
        return lastModifyTime;
    }
    
    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

}
