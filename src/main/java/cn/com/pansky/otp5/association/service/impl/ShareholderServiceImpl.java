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

import cn.com.pansky.otp5.association.controller.vo.ShareholderVO;
import cn.com.pansky.otp5.association.dao.IShareholderDao;
import cn.com.pansky.otp5.association.dao.po.Shareholder;
import cn.com.pansky.otp5.association.service.IShareholderService;
import cn.com.pansky.otp5.common.IDGenerator;
import cn.com.pansky.otp5.common.SystemContext;


@Service("shareholderService")  
public class ShareholderServiceImpl implements IShareholderService {

    @Resource  
    private IShareholderDao ShareholderDao;
    
    @Override
    public Page<ShareholderVO> findByPage(Map params) {
        int page = SystemContext.getCurrent();
        int pageSize = SystemContext.getPageSize();
        
//        System.out.println("page=====s=====>>>>" + page);
//        System.out.println("pageSize===s=======>>>>" + pageSize);
        PageHelper.startPage(page, pageSize);
        
        Page<Shareholder> pagePOs = (Page<Shareholder>) ShareholderDao.findByPage(params);
        Page<ShareholderVO> pageVOs = new Page<ShareholderVO>();
        
        try {
            BeanUtils.copyProperties(pageVOs, pagePOs);
        } catch (IllegalAccessException | InvocationTargetException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        List<ShareholderVO> vos = new ArrayList<ShareholderVO>();
        for(Shareholder po : pagePOs){
            ShareholderVO vo = new ShareholderVO();
//            BeanUtils.copyProperties(po, vo);
            try {
                BeanUtils.copyProperties(vo, po);
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
    public void insert(ShareholderVO vo) {
//        DataSourceSwitch.setDataSourceType(DataSourceInstances.READ);  
        vo.setId(IDGenerator.getUUID());
        vo.setEnable("1");
        Shareholder po = new Shareholder();
        try {
            BeanUtils.copyProperties(po, vo);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ShareholderDao.insert(po);
    }

    @Override
    public ShareholderVO selectByPrimaryKey(String id) {
        Shareholder po = ShareholderDao.selectByPrimaryKey(id);
        ShareholderVO vo = new ShareholderVO();
        try {
            BeanUtils.copyProperties(vo, po);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return vo;
    }

    @Override
    public void updateByPrimaryKey(ShareholderVO vo) {
        Shareholder po = new Shareholder();
        try {
            BeanUtils.copyProperties(po, vo);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ShareholderDao.updateByPrimaryKey(po);
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
