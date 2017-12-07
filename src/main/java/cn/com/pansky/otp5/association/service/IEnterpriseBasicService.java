package cn.com.pansky.otp5.association.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import cn.com.pansky.otp5.association.controller.vo.EnterpriseBasicVO;
import cn.com.pansky.otp5.association.dao.po.EnterpriseBasic;
import cn.com.pansky.otp5.baseplatform.service.IBaseService;

/**
 * 
 * @ClassName IEnterpriseBasicService
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年9月25日 下午7:44:28
 * @version 1.0.0
 */
public interface IEnterpriseBasicService extends IBaseService<EnterpriseBasicVO>{

//    List<TechRelate> selectByEID(String eId);
    public Map<String, List<EnterpriseBasic>> findAllEnterpriseByEnable(String enable);
    
    public Map<String, List<EnterpriseBasic>> findPublicEnterprise(String enable);
   
    public Map<String, List<EnterpriseBasic>> findHighEnterprise(String enable);

    public List<EnterpriseBasic> findListAllEnterpriseByEnable(String enable);
    
    public  List<EnterpriseBasicVO> findByParams(Map params);

    public void updatePositionByPrimaryKey(EnterpriseBasicVO vo);
    
    public boolean checkCode(EnterpriseBasicVO vo) throws IllegalAccessException, InvocationTargetException;
}
