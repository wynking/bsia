package cn.com.pansky.otp5.baseplatform.dao;

import org.mybatis.spring.annotation.MapperScan;

import cn.com.pansky.otp5.baseplatform.dao.po.User;

@MapperScan
public interface IUserDao extends IBaseDao<User>{
	
    public User getUserByLoginNameAndPwd(String loginName,String password);
    
    
}