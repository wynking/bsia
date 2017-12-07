package cn.com.pansky.otp5.association.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.com.pansky.otp5.association.controller.vo.ContactInfoVO;
import cn.com.pansky.otp5.association.dao.IContactInfoDao;
import cn.com.pansky.otp5.association.dao.po.ContactInfo;
import cn.com.pansky.otp5.association.dao.po.EnterpriseBasic;
import cn.com.pansky.otp5.association.service.IContactInfoService;
import cn.com.pansky.otp5.baseplatform.dao.po.Dictionary;
import cn.com.pansky.otp5.common.DictionaryUtil;
import cn.com.pansky.otp5.common.SystemContext;
import cn.com.pansky.otp5.common.IDGenerator;


@Service("contactInfoService")  
public class ContactInfoServiceImpl implements IContactInfoService {


    @Resource  
    private IContactInfoDao contactInfoDao;
    
    @Override
    public Page<ContactInfoVO> findByPage(Map params) {
        int page = SystemContext.getCurrent();
        int pageSize = SystemContext.getPageSize();
        
//        System.out.println("page=====s=====>>>>" + page);
//        System.out.println("pageSize===s=======>>>>" + pageSize);
        PageHelper.startPage(page, pageSize);
        
        Page<ContactInfo> pagePOs = (Page<ContactInfo>) contactInfoDao.findByPage(params);
        Page<ContactInfoVO> pageVOs = new Page<ContactInfoVO>();
       
        BeanUtils.copyProperties(pagePOs, pageVOs);
        
        List<ContactInfoVO> vos = new ArrayList<ContactInfoVO>();
        for(ContactInfo po : pagePOs){
            ContactInfoVO vo = new ContactInfoVO();
            BeanUtils.copyProperties(po, vo);
            vo.setJobName(DictionaryUtil.getNameByCode(vo.getJob(), (List<Dictionary>)params.get("dics")));
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
    public void insert(ContactInfoVO vo) {
        // TODO Auto-generated method stub
        vo.setId(IDGenerator.getUUID());
        vo.setEnable("1");
        ContactInfo po = new ContactInfo();
        BeanUtils.copyProperties(vo, po);
        contactInfoDao.insert(po);
    }

    @Override
    public ContactInfoVO selectByPrimaryKey(String id) {
        ContactInfo po = contactInfoDao.selectByPrimaryKey(id);
        ContactInfoVO vo = new ContactInfoVO();
        BeanUtils.copyProperties(po, vo);
        return vo;
    }

    @Override
    public void updateByPrimaryKey(ContactInfoVO vo) {
        ContactInfo po = new ContactInfo();
        BeanUtils.copyProperties(vo, po);
        contactInfoDao.updateByPrimaryKey(po);
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
