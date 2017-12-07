package cn.com.pansky.otp5.baseplatform.dao.po;

import java.io.Serializable;
import java.util.Date;

public class BasePO implements Serializable {

	/**
     * @Field @serialVersionUID : TODO(这里用一句话描述这个类的作用)
     */
    private static final long serialVersionUID = 1L;
    /*
	 * 创建人
	 */
	private String creationUser;
	/*
	 * 创建时间
	 */
	private Date creationTime;
	/*
	 * 最后修改人
	 */
	private String lastModifyUser;
	/*
	 * 最后修改时间
	 */
	private Date lastModifyTime;
    
    public String getCreationUser() {
        return creationUser;
    }
    
    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }
    
    public Date getCreationTime() {
        return creationTime;
    }
    
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
    
    public String getLastModifyUser() {
        return lastModifyUser;
    }
    
    public void setLastModifyUser(String lastModifyUser) {
        this.lastModifyUser = lastModifyUser;
    }
    
    public Date getLastModifyTime() {
        return lastModifyTime;
    }
    
    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

}
