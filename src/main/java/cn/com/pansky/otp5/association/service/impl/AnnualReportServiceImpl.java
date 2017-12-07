package cn.com.pansky.otp5.association.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.com.pansky.otp5.association.controller.vo.AnnualReportVO;
import cn.com.pansky.otp5.association.dao.IAnnualReportDao;
import cn.com.pansky.otp5.association.dao.po.AnnualReport;
import cn.com.pansky.otp5.association.service.IAnnualReportService;
import cn.com.pansky.otp5.common.SystemContext;
import cn.com.pansky.otp5.common.IDGenerator;


@Service("annualReportService")  
public class AnnualReportServiceImpl implements IAnnualReportService {

    @Resource  
    private IAnnualReportDao annualReportDao;
    
    @Override
    public Page<AnnualReportVO> findByPage(Map params) {
        int page = SystemContext.getCurrent();
        int pageSize = SystemContext.getPageSize();
        
        PageHelper.startPage(page, pageSize);
        
        Page<AnnualReport> pagePOs = (Page<AnnualReport>) annualReportDao.findByPage(params);
        Page<AnnualReportVO> pageVOs = new Page<AnnualReportVO>();
        
        try {
            BeanUtils.copyProperties(pageVOs, pagePOs);
        } catch (IllegalAccessException | InvocationTargetException e1) {
            e1.printStackTrace();
        }
        
        List<AnnualReportVO> vos = new ArrayList<AnnualReportVO>();
        for(AnnualReport po : pagePOs){
            AnnualReportVO vo = new AnnualReportVO();
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
    public void insert(AnnualReportVO vo) {
        // TODO Auto-generated method stub
        vo.setId(IDGenerator.getUUID());
        vo.setEnable("1");
        AnnualReport po = new AnnualReport();
        try {
            BeanUtils.copyProperties(po, vo);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        annualReportDao.insert(po);
    }

    @Override
    public AnnualReportVO selectByPrimaryKey(String id) {
        AnnualReport po = annualReportDao.selectByPrimaryKey(id);
        AnnualReportVO vo = new AnnualReportVO();
        try {
            BeanUtils.copyProperties(vo, po);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return vo;
    }

    @Override
    public void updateByPrimaryKey(AnnualReportVO vo) {
        AnnualReport po = new AnnualReport();
        try {
            BeanUtils.copyProperties(po, vo);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        annualReportDao.updateByPrimaryKey(po);
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
