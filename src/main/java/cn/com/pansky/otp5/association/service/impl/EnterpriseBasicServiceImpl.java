package cn.com.pansky.otp5.association.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.com.pansky.otp5.association.controller.vo.EnterpriseBasicVO;
import cn.com.pansky.otp5.association.dao.IAnnualReportDao;
import cn.com.pansky.otp5.association.dao.IAptitudeRelateDao;
import cn.com.pansky.otp5.association.dao.IContactInfoDao;
import cn.com.pansky.otp5.association.dao.IEnterpriseBasicDao;
import cn.com.pansky.otp5.association.dao.IIndustryRelateDao;
import cn.com.pansky.otp5.association.dao.IKnowledgeRightDao;
import cn.com.pansky.otp5.association.dao.IProductionDao;
import cn.com.pansky.otp5.association.dao.IShareholderDao;
import cn.com.pansky.otp5.association.dao.ITechRelateDao;
import cn.com.pansky.otp5.association.dao.po.AptitudeRelate;
import cn.com.pansky.otp5.association.dao.po.EnterpriseBasic;
import cn.com.pansky.otp5.association.dao.po.IndustryRelate;
import cn.com.pansky.otp5.association.dao.po.TechRelate;
import cn.com.pansky.otp5.association.service.IEnterpriseBasicService;
import cn.com.pansky.otp5.baseplatform.dao.po.Dictionary;
import cn.com.pansky.otp5.baseplatform.enums.EnableTypeEnum;
import cn.com.pansky.otp5.common.ConstantUtil;
import cn.com.pansky.otp5.common.DateUtil;
import cn.com.pansky.otp5.common.DictionaryUtil;
import cn.com.pansky.otp5.common.IDGenerator;
import cn.com.pansky.otp5.common.SystemContext;

/**
 * 
 * @ClassName EnterpriseBasicServiceImpl
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年9月21日 下午6:18:57
 * @version 1.0.0
 */
@Service("enterpriseBasicService")  
public class EnterpriseBasicServiceImpl implements IEnterpriseBasicService{

    @Resource  
    private IEnterpriseBasicDao enterpriseBasicDao;

    @Resource  
    private ITechRelateDao techRelateDao;

    @Resource  
    private IAptitudeRelateDao aptitudeRelateDao;
    
    @Resource  
    private IIndustryRelateDao industryRelateDao;
    
    @Resource  
    private IAnnualReportDao annualReportDao;
    
    @Resource  
    private IContactInfoDao contactInfoDao;
    
    @Resource  
    private IKnowledgeRightDao knowledgeRightDao;
    
    @Resource  
    private IProductionDao productionDao;
    
    @Resource  
    private IShareholderDao shareholderDao;
    
    

    @Override
    public Page<EnterpriseBasicVO> findByPage(Map params) {
        int page = SystemContext.getCurrent();
        int pageSize = SystemContext.getPageSize();
        
//        System.out.println("page=====s=====>>>>" + page);
//        System.out.println("pageSize===s=======>>>>" + pageSize);
        PageHelper.startPage(page, pageSize);
        
        Page<EnterpriseBasic> pagePOs = (Page<EnterpriseBasic>) enterpriseBasicDao.findByPage(params);
        Page<EnterpriseBasicVO> pageVOs = new Page<EnterpriseBasicVO>();
        
        try {
            BeanUtils.copyProperties(pageVOs, pagePOs);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        
        List<EnterpriseBasicVO> vos = new ArrayList<EnterpriseBasicVO>();
        for(EnterpriseBasic po : pagePOs){
            EnterpriseBasicVO vo = new EnterpriseBasicVO();
            try {
                BeanUtils.copyProperties(vo, po);
                vo.valToName(vo, (List<Dictionary>)params.get(ConstantUtil.DICNATIONARY));
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
    public void insert(EnterpriseBasicVO vo) {
        vo.setId(IDGenerator.getUUID());
        vo.setEnable("1");
        EnterpriseBasic po = new EnterpriseBasic();
        try {
            BeanUtils.copyProperties(po, vo);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        enterpriseBasicDao.insert(po);
        addTechs(vo);
        addAptitudes(vo);
        addIndustrys(vo);
    }

    /*
     * (非 Javadoc)
     * Description:
     * @see cn.com.pansky.otp5.baseplatform.service.IBaseService#selectByPrimaryKey(java.lang.String)
     */
    @Override
    public EnterpriseBasicVO selectByPrimaryKey(String id) {
        EnterpriseBasic po = enterpriseBasicDao.selectByPrimaryKey(id);
        EnterpriseBasicVO vo = new EnterpriseBasicVO();
        Map<String,String> otherParams = new HashMap<String, String>();
        try {
            BeanUtils.copyProperties(vo, po);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        
        List<TechRelate> techPOs =  techRelateDao.selectByEID(id);
        String[] techs = new String[techPOs.size()];
        for(int i=0;i<techPOs.size();i++){
            techs[i] = techPOs.get(i).getCode();
        }
        vo.setTechs(techs);
        
        List<AptitudeRelate> aptitudePOs = aptitudeRelateDao.selectByEID(id);
        String[] aptitudes = new String[aptitudePOs.size()];
        for(int i=0;i<aptitudePOs.size();i++){
            String code = aptitudePOs.get(i).getCode();
            Date passTime = aptitudePOs.get(i).getPassTime();
            aptitudes[i] = code;
            
            try {
                if(passTime!=null){
                    otherParams.put("timeAptitudes_"+code, DateUtil.parseDateToStr(aptitudePOs.get(i).getPassTime()) );
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        vo.setAptitudes(aptitudes);
        vo.setOtherParams(otherParams);

        List<IndustryRelate> industryPOs = industryRelateDao.selectByEID(id);
        String[] industrys = new String[industryPOs.size()];
        for(int i=0;i<industryPOs.size();i++){
            industrys[i] = industryPOs.get(i).getCode();
        }
        vo.setIndustrys(industrys);
        
        
        return vo;
    }

   /* @Override
    public List<TechRelate> selectByEID(String eId) {
        // TODO Auto-generated method stub
        List<TechRelate> techs =  techRelateDao.selectByEID(eId);
        return techs;
    }*/

    @Override
    public void updateByPrimaryKey(EnterpriseBasicVO vo) {
        EnterpriseBasic po = enterpriseBasicDao.selectByPrimaryKey(vo.getId());
        try {
            vo.setLongitude(po.getLongitude());
            vo.setDimension(po.getDimension());
            BeanUtils.copyProperties(po, vo);
//            System.out.println("po==============>>>" + po);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        enterpriseBasicDao.updateByPrimaryKey(po);
        
        techRelateDao.deleteByEID(vo.getId());
        aptitudeRelateDao.deleteByEID(vo.getId());
        industryRelateDao.deleteByEID(vo.getId());
        addTechs(vo);
        addAptitudes(vo);
        addIndustrys(vo);
    }
	
    /**
     * 
     * @Description 
     * @param vo
     */
    private void addTechs(EnterpriseBasicVO vo){
        String[] techs = vo.getTechs();
        if(techs != null && techs.length>0){
            for(String code : techs){
                TechRelate techPO = new TechRelate();
                techPO.setId(IDGenerator.getUUID());
                techPO.setCode(code);
                techPO.seteId(vo.getId());
                techRelateDao.insert(techPO);
            }
        }
    }

    /**
     * 
     * @Description 
     * @param vo
     */
    private void addAptitudes(EnterpriseBasicVO vo){
        String[] aptitudes = vo.getAptitudes();
        Map<String,String> otherParams = vo.getOtherParams();
        if(aptitudes != null && aptitudes.length>0){
            for(String code : aptitudes){
                AptitudeRelate relate = new AptitudeRelate();
                relate.setId(IDGenerator.getUUID());
                relate.setCode(code);
                relate.seteId(vo.getId());
                Date passDate = null;
                try {
                    System.out.println("DDDDDD======ggg========>>>>"+otherParams.get("timeAptitudes_"+code));
                    passDate = DateUtil.parseStrToDate(otherParams.get("timeAptitudes_"+code));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                relate.setPassTime(passDate);
                aptitudeRelateDao.insert(relate);
            }
        }
    }
    

    private void addIndustrys(EnterpriseBasicVO vo){
        String[] industrys = vo.getIndustrys();
        if(industrys != null && industrys.length>0){
            for(String code : industrys){
                IndustryRelate relate = new IndustryRelate();
                relate.setId(IDGenerator.getUUID());
                relate.setCode(code);
                relate.seteId(vo.getId());
                industryRelateDao.insert(relate);
            }
        }
    }
    
    /*
     * (非 Javadoc)
     * Description:
     * @see cn.com.pansky.otp5.baseplatform.service.IBaseService#deleteByPrimaryKey(java.lang.String)
     */
    @Override
    public void deleteByPrimaryKey(String id) {
        //删除年报信息
        annualReportDao.updateEnableByEId(id,EnableTypeEnum.SC.getKey());
        //删除联系人信息
        contactInfoDao.updateEnableByEId(id,EnableTypeEnum.SC.getKey());
        //删除知识产权信息
        knowledgeRightDao.updateEnableByEId(id,EnableTypeEnum.SC.getKey());
        //删除产品信息
        productionDao.updateEnableByEId(id,EnableTypeEnum.SC.getKey());
        //删除股权信息
        shareholderDao.updateEnableByEId(id,EnableTypeEnum.SC.getKey());
        //删除企业关联技术领域的信息
        //techRelateDao.updateEnableByEId(id,EnableTypeEnum.SC.getKey());
            
        EnterpriseBasic po = enterpriseBasicDao.selectByPrimaryKey(id);
        po.setEnable(EnableTypeEnum.SC.getKey());
        //删除企业信息
        enterpriseBasicDao.updateByPrimaryKey(po);
    }

    /*
     * (非 Javadoc)
     * Description:
     * @see cn.com.pansky.otp5.association.service.IEnterpriseBasicService#findAllEnterpriseByEnable(java.lang.String)
     */
    @Override
    public Map<String, List<EnterpriseBasic>> findAllEnterpriseByEnable(String enable) {
        Map<String, List<EnterpriseBasic>> map = new HashMap<String,List<EnterpriseBasic>>();
        List<EnterpriseBasic> pos = enterpriseBasicDao.findAllEnterpriseByEnable(enable);
        
        for(EnterpriseBasic po : pos){
            String areaCode = po.getAddress();
            List<EnterpriseBasic> b = map.get(areaCode);
            if(b==null){
                List<EnterpriseBasic> c = new ArrayList<EnterpriseBasic>();
                c.add(po);
                map.put(areaCode, c);
            }else{
                b.add(po);
            }
        }
        return map;
    }
    

   
    /*
     * (非 Javadoc)
     * Description:
     * @see cn.com.pansky.otp5.association.service.IEnterpriseBasicService#findPublicEnterprise(java.lang.String)
     */
    @Override
    public Map<String, List<EnterpriseBasic>> findPublicEnterprise(String enable) {
        Map<String, List<EnterpriseBasic>> map = new HashMap<String,List<EnterpriseBasic>>();
        List<EnterpriseBasic> pos = enterpriseBasicDao.findAllEnterpriseByEnable(enable);
        
        for(EnterpriseBasic po : pos){
//            if("1".equals(po.getIsPublic())){}
            String areaCode = po.getAddress();
            List<EnterpriseBasic> b = map.get(areaCode);
            if(b==null){
                List<EnterpriseBasic> c = new ArrayList<EnterpriseBasic>();
                if("1".equals(po.getIsPublic())){
                    c.add(po);
                }
                map.put(areaCode, c);
            }else{
                b.add(po);
            }
        }
        return map;
    }

    /*
     * (非 Javadoc)
     * Description:
     * @see cn.com.pansky.otp5.association.service.IEnterpriseBasicService#findHighEnterprise(java.lang.String)
     */
    @Override
    public Map<String, List<EnterpriseBasic>> findHighEnterprise(String enable) {

        Map<String, List<EnterpriseBasic>> map = new HashMap<String,List<EnterpriseBasic>>();
        List<EnterpriseBasic> pos = enterpriseBasicDao.findAllEnterpriseByEnable(enable);
        
        for(EnterpriseBasic po : pos){
//            if("1".equals(po.getIsPublic())){}
            String areaCode = po.getAddress();
            List<EnterpriseBasic> b = map.get(areaCode);
            if(b==null){
                List<EnterpriseBasic> c = new ArrayList<EnterpriseBasic>();
                if("1".equals(po.getIsHigh())){
                    c.add(po);
                }
                map.put(areaCode, c);
            }else{
                b.add(po);
            }
        }
        return map;
    }

    /*
     * (非 Javadoc)
     * Description:
     * @see cn.com.pansky.otp5.association.service.IEnterpriseBasicService#findListAllEnterpriseByEnable(java.lang.String)
     */
    @Override
    public List<EnterpriseBasic> findListAllEnterpriseByEnable(String enable) {
        return enterpriseBasicDao.findAllEnterpriseByEnable(enable);
    }

    /*
     * (非 Javadoc)
     * Description:
     * @see cn.com.pansky.otp5.association.service.IEnterpriseBasicService#findByParams(java.util.Map)
     */
    @Override
    public List<EnterpriseBasicVO> findByParams(Map params) {
        List<EnterpriseBasic> pos = enterpriseBasicDao.findByParams(params);
        
        List<EnterpriseBasicVO> vos = new ArrayList<EnterpriseBasicVO>();
        for(EnterpriseBasic po : pos){
            EnterpriseBasicVO vo = new EnterpriseBasicVO();
            try {
                BeanUtils.copyProperties(vo, po);
                if(StringUtils.isNotEmpty(vo.getAddress())){
                    vo.setAddressName(DictionaryUtil.getRegionNameByCode(vo.getAddress(), (HttpServletRequest)params.get(ConstantUtil.REQUEST)));
                }
               
//              vo.valToName(vo, (List<Dictionary>)params.get(ConstantUtil.DICNATIONARY));
            
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            vos.add(vo);
        }
        
        return vos;
    }

    /*
     * (非 Javadoc)
     * Description:
     * @see cn.com.pansky.otp5.association.service.IEnterpriseBasicService#updatePositionByPrimaryKey(cn.com.pansky.otp5.association.controller.vo.EnterpriseBasicVO)
     */
    @Override
    public void updatePositionByPrimaryKey(EnterpriseBasicVO vo) {
        EnterpriseBasic po = enterpriseBasicDao.selectByPrimaryKey(vo.getId());
        po.setLongitude(vo.getLongitude());
        po.setDimension(vo.getDimension());
        enterpriseBasicDao.updateByPrimaryKey(po);
    }

    @Override
    public boolean checkCode(EnterpriseBasicVO vo) throws IllegalAccessException, InvocationTargetException {
        EnterpriseBasic po = new EnterpriseBasic();
        BeanUtils.copyProperties(po, vo);
        boolean flag = false;
        if(StringUtils.isNotEmpty(vo.getId())){
            int count = enterpriseBasicDao.checkCodeForUpdate(po);
            if(count==0){
                 flag = true;
            }
        }else{
            int count = enterpriseBasicDao.checkCodeForAdd(po);
            if(count==0){
                flag = true;
           }
        }
        
        return flag;
    }

    @Override
    public void updateEnabledByPrimaryKey(String enabled, String id) {
        // TODO Auto-generated method stub
        
    }
    
}
