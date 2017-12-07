package cn.com.pansky.otp5.association.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;

import cn.com.pansky.otp5.association.dao.po.EnterpriseBasic;
import cn.com.pansky.otp5.baseplatform.dao.IBaseDao;

@MapperScan
public interface IEnterpriseBasicDao extends IBaseDao<EnterpriseBasic> {
    
    public List<EnterpriseBasic> findAllEnterpriseByEnable(String enable);
    
    public List<EnterpriseBasic> findAllEnterprise();
    
    public List<EnterpriseBasic> findByParams(Map<String,Object> map);

    public int checkCodeForAdd(EnterpriseBasic po);
    
    public int checkCodeForUpdate(EnterpriseBasic po);
    
    
}