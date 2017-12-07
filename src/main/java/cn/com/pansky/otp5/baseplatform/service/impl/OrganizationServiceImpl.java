package cn.com.pansky.otp5.baseplatform.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.com.pansky.otp5.baseplatform.controller.vo.OrganizationVO;
import cn.com.pansky.otp5.baseplatform.dao.IOrganizationDao;
import cn.com.pansky.otp5.baseplatform.dao.po.Organization;
import cn.com.pansky.otp5.baseplatform.enums.EnableTypeEnum;
import cn.com.pansky.otp5.baseplatform.service.IOrganizationService;
import cn.com.pansky.otp5.common.IDGenerator;
import cn.com.pansky.otp5.common.SystemContext;

@Service("organizationService")  
public class OrganizationServiceImpl implements IOrganizationService{
	
	@Resource 
    private IOrganizationDao organizationDao;
	
	
	
    @Override
    public Page<OrganizationVO> findByPage(Map params) {
        int page = SystemContext.getCurrent();
        int pageSize = SystemContext.getPageSize();
        
        PageHelper.startPage(page, pageSize);
        
        Page<Organization> pagePOs = (Page<Organization>) organizationDao.findByPage(params);
        Page<OrganizationVO> pageVOs = new Page<OrganizationVO>();
        
        try {
            BeanUtils.copyProperties(pageVOs, pagePOs);
        } catch (IllegalAccessException | InvocationTargetException e1) {
            e1.printStackTrace();
        }
        
        List<OrganizationVO> vos = new ArrayList<OrganizationVO>();
        for(Organization po : pagePOs){
            OrganizationVO vo = new OrganizationVO();
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



    @Override
    public void insert(OrganizationVO vo) {
        vo.setId(IDGenerator.getUUID());
        vo.setEnabled(EnableTypeEnum.QY.getKey());
        Organization po = new Organization();
        try {
            BeanUtils.copyProperties(po, vo);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        organizationDao.insert(po);
    }



    @Override
    public OrganizationVO selectByPrimaryKey(String id) {
        Organization po = organizationDao.selectByPrimaryKey(id);
        OrganizationVO vo = new OrganizationVO();
        try {
            BeanUtils.copyProperties(vo, po);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return vo;
    }



    @Override
    public void updateByPrimaryKey(OrganizationVO vo) {
        Organization po = new Organization();
        try {
            BeanUtils.copyProperties(po, vo);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        organizationDao.updateByPrimaryKey(po);
    }



    @Override
    public void deleteByPrimaryKey(String id) {
        
    }


    @Override
    public void updateEnabledByPrimaryKey(String enabled, String id) {
        organizationDao.updateEnabledByPrimaryKey(enabled, id);
    }  

    
    @Override
    public List<OrganizationVO> selectDatasByPId(String pId) {
        List<OrganizationVO> vos = new ArrayList<OrganizationVO>();
        List<Organization> pos = organizationDao.selectDatasByPId(pId);
        for(Organization po : pos){
            OrganizationVO vo = new OrganizationVO();
            try {
                BeanUtils.copyProperties(vo, po);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            vos.add(vo);
        }
        return  vos;
    }



   /*
    * (非 Javadoc)
    * Description:
    * @see cn.com.pansky.otp5.baseplatform.service.IResourceService#selectAll()
    */
    @Override
    public List<OrganizationVO> selectAll() {
        List<OrganizationVO> vos = new ArrayList<OrganizationVO>();
        List<Organization> pos = organizationDao.selectAll();
        for(Organization po : pos){
            OrganizationVO vo = new OrganizationVO();
            try {
                BeanUtils.copyProperties(vo, po);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            vos.add(vo);
        }
        return  vos;
    }
    
}
