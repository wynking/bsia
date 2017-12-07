package cn.com.pansky.otp5.common;

import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName TreeBean
 * @Description 用于存储和传递树形数据
 * @author Administrator
 * @Date 2017年9月17日 上午12:49:42
 * @version 1.0.0
 */
public class TreeBean {
    
    private String id;
    
    private String name;
    
    private String pId;
    
    private boolean isParent;
    
    private Map<String,String> params;
    
    private List<TreeBean> children;

    
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

    
    public String getpId() {
        return pId;
    }

    
    public void setpId(String pId) {
        this.pId = pId;
    }

    
    public List<TreeBean> getChildren() {
        return children;
    }

    
    public void setChildren(List<TreeBean> children) {
        this.children = children;
    }


    
    public boolean getIsParent() {
        return isParent;
    }


    
    public void setIsParent(boolean isParent) {
        this.isParent = isParent;
    }


    
    public Map<String, String> getParams() {
        return params;
    }


    
    public void setParams(Map<String, String> params) {
        this.params = params;
    }


    
    public void setParent(boolean isParent) {
        this.isParent = isParent;
    }
    
    
    
}
