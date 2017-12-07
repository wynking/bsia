package cn.com.pansky.otp5.association.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.com.pansky.otp5.association.controller.vo.ProductionVO;
import cn.com.pansky.otp5.association.dao.IProductionDao;
import cn.com.pansky.otp5.association.dao.IProductionFieldRelateDao;
import cn.com.pansky.otp5.association.dao.po.Production;
import cn.com.pansky.otp5.association.dao.po.ProductionFieldRelate;
import cn.com.pansky.otp5.association.dao.po.TechRelate;
import cn.com.pansky.otp5.association.service.IProductionService;
import cn.com.pansky.otp5.common.SystemContext;
import cn.com.pansky.otp5.common.IDGenerator;

/**
 * 
 * @ClassName ProductionServiceImpl
 * @Description  
 * @author Administrator
 * @Date 2017年9月28日 下午10:44:14
 * @version 1.0.0
 */
@Service("productionService")  
public class ProductionServiceImpl implements IProductionService {

    @Resource  
    private IProductionDao productionDao;
    
    @Resource  
    private IProductionFieldRelateDao productionFieldRelateDao;
    
    @Override
    public Page<ProductionVO> findByPage(Map params) {
        int page = SystemContext.getCurrent();
        int pageSize = SystemContext.getPageSize();
        
        PageHelper.startPage(page, pageSize);
        
        Page<Production> pagePOs = (Page<Production>) productionDao.findByPage(params);
        Page<ProductionVO> pageVOs = new Page<ProductionVO>();
        
        try {
            BeanUtils.copyProperties(pageVOs, pagePOs);
        } catch (IllegalAccessException | InvocationTargetException e1) {
            e1.printStackTrace();
        }
        
        List<ProductionVO> vos = new ArrayList<ProductionVO>();
        for(Production po : pagePOs){
            ProductionVO vo = new ProductionVO();
//          BeanUtils.copyProperties(po, vo);
            try {
                BeanUtils.copyProperties(vo, po);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
//          vo.setJobName(DictionaryUtil.getNameByCode(vo.getJob(), (List<Dictionary>)params.get("dics")));
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
    public void insert(ProductionVO vo) {
        // TODO Auto-generated method stub
        vo.setId(IDGenerator.getUUID());
        vo.setEnable("1");
        Production po = new Production();
        try {
            BeanUtils.copyProperties(po, vo);
            addFwlys(vo);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        productionDao.insert(po);
    }

    @Override
    public ProductionVO selectByPrimaryKey(String id) {
        Production po = productionDao.selectByPrimaryKey(id);
        for(ProductionFieldRelate ss : po.getFwlypos()){
            System.out.println("===================AAA=================>>>>>" + ss.getFwlyCode());
        }
        
        ProductionVO vo = new ProductionVO();
        try {
            BeanUtils.copyProperties(vo, po);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
//        List<ProductionFieldRelate> prfs =  productionFieldRelateDao.selectByProducttionID(id);
        List<ProductionFieldRelate> fwlypos = po.getFwlypos();
        if(!CollectionUtils.isEmpty(fwlypos)){
            String[] fwlys = new String[fwlypos.size()];
            for(int i=0;i<fwlypos.size();i++){
                fwlys[i] = fwlypos.get(i).getFwlyCode();
            }
            vo.setFwlys(fwlys);
        }
        
        return vo;
    }

    @Override
    public void updateByPrimaryKey(ProductionVO vo) {
        Production po = new Production();
        try {
            BeanUtils.copyProperties(po, vo);
        } catch(IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        productionDao.updateByPrimaryKey(po);
        productionFieldRelateDao.deleteByProducttionID(vo.getId());
        addFwlys(vo);
    }
    
    
    
    
    private void addFwlys(ProductionVO vo){
        String[] fwlys = vo.getFwlys();
        if(fwlys != null && fwlys.length>0){
            for(String code : fwlys){
                ProductionFieldRelate pfr = new ProductionFieldRelate();
                pfr.setId(IDGenerator.getUUID());
                pfr.setFwlyCode(code);
                pfr.setProductionId(vo.getId());
                productionFieldRelateDao.insert(pfr);
            }
        }
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
