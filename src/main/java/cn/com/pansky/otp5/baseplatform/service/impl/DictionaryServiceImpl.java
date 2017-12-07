package cn.com.pansky.otp5.baseplatform.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.com.pansky.otp5.association.dao.po.EnterpriseBasic;
import cn.com.pansky.otp5.baseplatform.dao.IDictionaryDao;
import cn.com.pansky.otp5.baseplatform.dao.po.Dictionary;
import cn.com.pansky.otp5.baseplatform.enums.EnableTypeEnum;
import cn.com.pansky.otp5.baseplatform.service.IDictionaryService;
import cn.com.pansky.otp5.common.SystemContext;
import cn.com.pansky.otp5.common.IDGenerator;

@Service("dictionaryService")  
public class DictionaryServiceImpl implements IDictionaryService{
	
	@Resource  
    private IDictionaryDao dictionaryDao;

	@Override
    public Page<Dictionary> findByPage(Map params) {
        int page = SystemContext.getCurrent();
        int pageSize = SystemContext.getPageSize();
//        System.out.println("page=====s=====>>>>" + page);
//        System.out.println("pageSize===s=======>>>>" + pageSize);
        PageHelper.startPage(page, pageSize);
        
        Page<Dictionary> pagePOs = (Page<Dictionary>) dictionaryDao.findByPage(params);
        
        return pagePOs;
    
    }

    @Override
    public void insert(Dictionary dic) {
        Map<String,Object> params = new HashMap<String, Object>();
       // Dictionary pDic = dictionaryDao.selectByCodeAndType(params);
        Dictionary pDic = dictionaryDao.selectByPrimaryKey(dic.getpId());
        
        dic.setId(IDGenerator.getUUID());
        dic.setEnable(EnableTypeEnum.QY.getKey());
        //上级节点ID为0，那么则是新增码表类型，否则是新增码表值
        if(dic.getpCode().equals("0")){
            dic.setIsDicName("1");
            dic.setType(dic.getCode());
        }else{
            dic.setIsDicName("0");
            dic.setType(pDic.getType());
        }
        dictionaryDao.insert(dic);
    }

    @Override
    public List<Dictionary> getAllDictionaryByEnable(String enable) {
        List<Dictionary> list = dictionaryDao.getAllDictionaryByEnable("1");
        return list;
    }

    @Override
    public Dictionary selectByPrimaryKey(String id) {
        return dictionaryDao.selectByPrimaryKey(id);
    }

    @Override
    public void updateByPrimaryKey(Dictionary vo) {
//        Dictionary po = new Dictionary();
//        try {
//            BeanUtils.copyProperties(po, vo);
//        } catch (IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }
        dictionaryDao.updateByPrimaryKey(vo);
    }

    @Override
    public void deleteByPrimaryKey(String id) {
        Dictionary po = dictionaryDao.selectByPrimaryKey(id);
        po.setEnable(EnableTypeEnum.SC.getKey());
        dictionaryDao.updateByPrimaryKey(po);
    }

    @Override
    public List<Dictionary> showDictionaryTree(Map<String,String> params) {
        return  dictionaryDao.showDictionaryTree(params);
    }

    @Override
    public void importRegion(List<Dictionary> dics) {
        for(Dictionary dic : dics){
            dictionaryDao.insert(dic);
        }
        
    }

    @Override
    public void updateEnabledByPrimaryKey(String enabled, String id) {
        // TODO Auto-generated method stub
        
    }
	

}
