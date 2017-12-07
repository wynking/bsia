package cn.com.pansky.otp5.baseplatform.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.com.pansky.otp5.baseplatform.controller.vo.UserVO;
import cn.com.pansky.otp5.baseplatform.dao.IUserDao;
import cn.com.pansky.otp5.baseplatform.dao.po.User;
import cn.com.pansky.otp5.baseplatform.service.IUserService;
import cn.com.pansky.otp5.common.SystemContext;
import cn.com.pansky.otp5.common.IDGenerator;

@Service("userService")  
public class UserServiceImpl implements IUserService{
	
	@Resource  
    private IUserDao userDao;  

	
	@Override
	public void insert(UserVO vo) {
	    vo.setId(IDGenerator.getUUID());
	    User user = new User();
	    try {
            BeanUtils.copyProperties(user, vo);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
		userDao.insert(user);
	}


	/*
	 * (非 Javadoc)
	 * Description:
	 * @see cn.com.pansky.otp5.baseplatform.service.IBaseService#findByPage(java.util.Map)
	 */
	@Override
	public Page<UserVO> findByPage(Map params) {
	    
        int page = SystemContext.getCurrent();
        int pageSize = SystemContext.getPageSize();
        
        PageHelper.startPage(page, pageSize);
        
        Page<User> pagePOs = (Page<User>) userDao.findByPage(params);
        Page<UserVO> pageVOs = new Page<UserVO>();
        
        try {
            BeanUtils.copyProperties(pageVOs, pagePOs);
        } catch (IllegalAccessException | InvocationTargetException e1) {
            e1.printStackTrace();
        }
        
        List<UserVO> vos = new ArrayList<UserVO>();
        for(User po : pagePOs){
            UserVO vo = new UserVO();
            try {
                BeanUtils.copyProperties(vo, po);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            
            vos.add(vo);
        }
        pageVOs.addAll(vos);
        return pageVOs;
    
    }


	/*
	 * (非 Javadoc)
	 * Description:
	 * @see cn.com.pansky.otp5.baseplatform.service.IUserService#getUserByLoginNameAndPwd(java.lang.String, java.lang.String)
	 */
    @Override
    public UserVO getUserByLoginNameAndPwd(String loginName, String password) {
        User user = userDao.getUserByLoginNameAndPwd(loginName, password);
        if(!StringUtils.isEmpty(user)){
            UserVO vo = new UserVO();
            try {
                BeanUtils.copyProperties(vo, user);
            } catch (IllegalAccessException | InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return vo;
        }
        return null;
    }


   

    @Override
    public UserVO selectByPrimaryKey(String id) {
        User po = userDao.selectByPrimaryKey(id);
        UserVO vo = new UserVO();
        try {
            BeanUtils.copyProperties(vo, po);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return vo;
    }


    @Override
    public void updateByPrimaryKey(UserVO vo) {
        User po = new User();
        try {
            BeanUtils.copyProperties(po, vo);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        userDao.updateByPrimaryKey(po);
    }


    @Override
    public void deleteByPrimaryKey(String id) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void updateEnabledByPrimaryKey(String enabled, String id) {
        // TODO Auto-generated method stub
        
    }



}
