package cn.com.pansky.otp5.baseplatform.service;

import java.util.List;

import cn.com.pansky.otp5.baseplatform.controller.vo.UserVO;
import cn.com.pansky.otp5.baseplatform.dao.po.User;

public interface IUserService extends IBaseService<UserVO>{
	
	/**
	 * 
	 * @Description (TODO这里用一句话描述这个方法的作用)
	 * @param loginName
	 * @param password
	 * @return
	 */
	public UserVO getUserByLoginNameAndPwd(String loginName,String password);
	
}
