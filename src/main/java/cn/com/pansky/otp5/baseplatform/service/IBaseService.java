package cn.com.pansky.otp5.baseplatform.service;

import java.util.Map;

import com.github.pagehelper.Page;

import cn.com.pansky.otp5.baseplatform.controller.vo.UserVO;

/**
 * 
 * @ClassName IBaseService
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author wyn
 * @Date 2017年9月11日 下午11:49:22
 * @version 1.0.0
 * @param <T>
 */
public interface IBaseService<T> {
	
	public Page<T> findByPage(Map params);
	
	public void insert(T vo); 
	
	public T selectByPrimaryKey(String id);
	
	public void updateByPrimaryKey(T vo);
	
    public void deleteByPrimaryKey(String id);
    
    public void updateEnabledByPrimaryKey(String enabled,String id);
	
}
