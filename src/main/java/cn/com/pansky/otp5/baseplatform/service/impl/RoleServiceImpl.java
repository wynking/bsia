package cn.com.pansky.otp5.baseplatform.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.com.pansky.otp5.baseplatform.controller.vo.RoleVO;
import cn.com.pansky.otp5.baseplatform.dao.IRoleDao;
import cn.com.pansky.otp5.baseplatform.dao.IRoleResourceDao;
import cn.com.pansky.otp5.baseplatform.dao.po.Role;
import cn.com.pansky.otp5.baseplatform.dao.po.RoleResource;
import cn.com.pansky.otp5.baseplatform.enums.EnableTypeEnum;
import cn.com.pansky.otp5.baseplatform.service.IRoleService;
import cn.com.pansky.otp5.common.IDGenerator;
import cn.com.pansky.otp5.common.SystemContext;

@Service("roleService")  
public class RoleServiceImpl implements IRoleService{

    @Resource  
    private IRoleDao roleDao;
    
    @Resource  
    private IRoleResourceDao roleResourceDao;
	

    private void insertRoleResource(RoleVO vo) {
        String rIds = vo.getResourceIds();
        String[] rIdArr = rIds.split(",");
        for(String rId : rIdArr){
            if(StringUtils.isNotEmpty(rId) ){
                RoleResource po = new RoleResource();
                po.setId(IDGenerator.getUUID());
                po.setRoleId(vo.getId());
                po.setResourceId(rId);
                roleResourceDao.insert(po);
            }
        }
    }
    
	/*
	 * (非 Javadoc)
	 * Description:
	 * @see cn.com.pansky.otp5.baseplatform.service.IBaseService#findByPage(java.util.Map)
	 */
    @Override
    public Page<RoleVO> findByPage(Map params) {
        int page = SystemContext.getCurrent();
        int pageSize = SystemContext.getPageSize();
        PageHelper.startPage(page, pageSize);
        Page<Role> pagePOs = (Page<Role>) roleDao.findByPage(params);
        Page<RoleVO> pageVOs = new Page<RoleVO>();
        try {
            BeanUtils.copyProperties(pageVOs, pagePOs);
        } catch (IllegalAccessException | InvocationTargetException e1) {
            e1.printStackTrace();
        }
        List<RoleVO> vos = new ArrayList<RoleVO>();
        for(Role po : pagePOs){
            RoleVO vo = new RoleVO();
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
     * @see cn.com.pansky.otp5.baseplatform.service.IBaseService#insert(java.lang.Object)
     */
    @Override
    public void insert(RoleVO vo) {
        vo.setId(IDGenerator.getUUID());
        Role Role = new Role();
        try {
            BeanUtils.copyProperties(Role, vo);
            Role.setEnabled(EnableTypeEnum.QY.getKey());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        roleDao.insert(Role);
        insertRoleResource(vo);
    }


    /*
     * (非 Javadoc)
     * Description:
     * @see cn.com.pansky.otp5.baseplatform.service.IBaseService#selectByPrimaryKey(java.lang.String)
     */
    @Override
    public RoleVO selectByPrimaryKey(String id) {
        Role po = roleDao.selectByPrimaryKey(id);
        List<RoleResource> rrs = roleResourceDao.selectByRoleId(id);
        RoleVO vo = new RoleVO();
        try {
            BeanUtils.copyProperties(vo, po);
            vo.setRrs(rrs);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return vo;
    }

    /*
     * (非 Javadoc)
     * Description:
     * @see cn.com.pansky.otp5.baseplatform.service.IBaseService#updateByPrimaryKey(java.lang.Object)
     */
    @Override
    public void updateByPrimaryKey(RoleVO vo) {
        Role po = new Role();
        try {
            BeanUtils.copyProperties(po, vo);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        roleDao.updateByPrimaryKey(po);
        roleResourceDao.deleteByRoleId(vo.getId());
        insertRoleResource(vo);
        
    }

    /*
     * (非 Javadoc)
     * Description:
     * @see cn.com.pansky.otp5.baseplatform.service.IBaseService#deleteByPrimaryKey(java.lang.String)
     */
    @Override
    public void deleteByPrimaryKey(String id) {
        // TODO Auto-generated method stub
        
    }

    /*
     * (非 Javadoc)
     * Description:
     * @see cn.com.pansky.otp5.baseplatform.service.IBaseService#updateEnabledByPrimaryKey(java.lang.String, java.lang.String)
     */
    @Override
    public void updateEnabledByPrimaryKey(String enabled, String id) {
        roleDao.updateEnabledByPrimaryKey(enabled, id);
    }  

	

}
