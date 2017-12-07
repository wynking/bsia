package cn.com.pansky.otp5.baseplatform.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.com.pansky.otp5.association.controller.vo.AnnualReportVO;
import cn.com.pansky.otp5.association.dao.po.AnnualReport;
import cn.com.pansky.otp5.baseplatform.controller.vo.ResourceVO;
import cn.com.pansky.otp5.baseplatform.dao.IResourceDao;
import cn.com.pansky.otp5.baseplatform.dao.po.Dictionary;
import cn.com.pansky.otp5.baseplatform.dao.po.Resource;
import cn.com.pansky.otp5.baseplatform.enums.EnableTypeEnum;
import cn.com.pansky.otp5.baseplatform.service.IResourceService;
import cn.com.pansky.otp5.common.IDGenerator;
import cn.com.pansky.otp5.common.SystemContext;

@Service("resourceService")  
public class ResourceServiceImpl implements IResourceService{
	
	@javax.annotation.Resource  
    private IResourceDao resourceDao;
	
	
	
    @Override
    public Page<ResourceVO> findByPage(Map params) {
        int page = SystemContext.getCurrent();
        int pageSize = SystemContext.getPageSize();
        
        PageHelper.startPage(page, pageSize);
        
        Page<Resource> pagePOs = (Page<Resource>) resourceDao.findByPage(params);
        Page<ResourceVO> pageVOs = new Page<ResourceVO>();
        
        try {
            BeanUtils.copyProperties(pageVOs, pagePOs);
        } catch (IllegalAccessException | InvocationTargetException e1) {
            e1.printStackTrace();
        }
        
        List<ResourceVO> vos = new ArrayList<ResourceVO>();
        for(Resource po : pagePOs){
            ResourceVO vo = new ResourceVO();
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
    public void insert(ResourceVO vo) {
        vo.setId(IDGenerator.getUUID());
        vo.setEnabled(EnableTypeEnum.QY.getKey());
        Resource po = new Resource();
        try {
            BeanUtils.copyProperties(po, vo);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        resourceDao.insert(po);
    }



    @Override
    public ResourceVO selectByPrimaryKey(String id) {
        Resource po = resourceDao.selectByPrimaryKey(id);
        ResourceVO vo = new ResourceVO();
        try {
            BeanUtils.copyProperties(vo, po);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return vo;
    }



    @Override
    public void updateByPrimaryKey(ResourceVO vo) {
        Resource po = new Resource();
        try {
            BeanUtils.copyProperties(po, vo);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        resourceDao.updateByPrimaryKey(po);
    }



    @Override
    public void deleteByPrimaryKey(String id) {
        
    }



    @Override
    public void updateEnabledByPrimaryKey(String enabled, String id) {
        resourceDao.updateEnabledByPrimaryKey(enabled, id);
    }  

    /*
     * (非 Javadoc)
     * Description:
     * @see cn.com.pansky.otp5.baseplatform.service.IResourceService#selectResourcesByPId(java.lang.String)
     */
    @Override
    public List<ResourceVO> selectResourcesByPId(String pId) {
        List<ResourceVO> vos = new ArrayList<ResourceVO>();
        List<Resource> pos = resourceDao.selectResourcesByPId(pId);
        for(Resource po : pos){
            ResourceVO vo = new ResourceVO();
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
    public List<ResourceVO> selectAll() {
        List<ResourceVO> vos = new ArrayList<ResourceVO>();
        List<Resource> pos = resourceDao.selectAll();
        for(Resource po : pos){
            ResourceVO vo = new ResourceVO();
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
