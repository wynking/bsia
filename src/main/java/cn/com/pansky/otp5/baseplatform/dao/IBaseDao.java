package cn.com.pansky.otp5.baseplatform.dao;

import java.util.List;
import java.util.Map;

public interface IBaseDao<T> {
	
    public List<T> findByPage(Map params);
    
    public void insert(T po);
    
    public T selectByPrimaryKey(String id);

    public void updateByPrimaryKey(T po);
    
    public void updateEnabledByPrimaryKey(String enabled,String id);
    
}
