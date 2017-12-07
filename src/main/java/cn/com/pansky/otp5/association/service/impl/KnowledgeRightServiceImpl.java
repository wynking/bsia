package cn.com.pansky.otp5.association.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.com.pansky.otp5.association.controller.vo.KnowledgeRightVO;
import cn.com.pansky.otp5.association.dao.IKnowledgeRightDao;
import cn.com.pansky.otp5.association.dao.po.KnowledgeRight;
import cn.com.pansky.otp5.association.service.IKnowledgeRightService;
import cn.com.pansky.otp5.common.SystemContext;
import cn.com.pansky.otp5.common.ConstantUtil;
import cn.com.pansky.otp5.common.DictionaryUtil;
import cn.com.pansky.otp5.common.IDGenerator;


@Service("knowledgeRightService")  
public class KnowledgeRightServiceImpl implements IKnowledgeRightService {

    @Resource  
    private IKnowledgeRightDao knowledgeRightDao;
    
    @Override
    public Page<KnowledgeRightVO> findByPage(Map params) {
        int page = SystemContext.getCurrent();
        int pageSize = SystemContext.getPageSize();

        PageHelper.startPage(page, pageSize);
        
        Page<KnowledgeRight> pagePOs = (Page<KnowledgeRight>) knowledgeRightDao.findByPage(params);
        Page<KnowledgeRightVO> pageVOs = new Page<KnowledgeRightVO>();
        
        try {
            BeanUtils.copyProperties(pageVOs, pagePOs);
        } catch (IllegalAccessException | InvocationTargetException e1) {
            e1.printStackTrace();
        }
        
        List<KnowledgeRightVO> vos = new ArrayList<KnowledgeRightVO>();
        for(KnowledgeRight po : pagePOs){
            KnowledgeRightVO vo = new KnowledgeRightVO();
//            BeanUtils.copyProperties(po, vo);
            try {
                BeanUtils.copyProperties(vo, po);
                
                vo.setTypeName(DictionaryUtil.getNameByCodeType((HttpServletRequest)params.get(ConstantUtil.REQUEST), po.getType(), ConstantUtil.DIC_INFO_KNOWLEDGETYPE));
            } catch (IllegalAccessException | InvocationTargetException e) {
                // TODO Auto-generated catch block
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
    public void insert(KnowledgeRightVO vo) {
        // TODO Auto-generated method stub
        vo.setId(IDGenerator.getUUID());
        vo.setEnable("1");
        KnowledgeRight po = new KnowledgeRight();
        try {
            BeanUtils.copyProperties(po, vo);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        knowledgeRightDao.insert(po);
    }

    @Override
    public KnowledgeRightVO selectByPrimaryKey(String id) {
        KnowledgeRight po = knowledgeRightDao.selectByPrimaryKey(id);
        KnowledgeRightVO vo = new KnowledgeRightVO();
        try {
            BeanUtils.copyProperties(vo, po);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return vo;
    }

    @Override
    public void updateByPrimaryKey(KnowledgeRightVO vo) {
        KnowledgeRight po = new KnowledgeRight();
        try {
            BeanUtils.copyProperties(po, vo);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        knowledgeRightDao.updateByPrimaryKey(po);
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
