package cn.com.pansky.otp5.baseplatform.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;

import cn.com.pansky.otp5.baseplatform.controller.vo.ResourceVO;
import cn.com.pansky.otp5.baseplatform.dao.po.Organization;
import cn.com.pansky.otp5.baseplatform.dao.po.Organization;

/**
 * 
 * @ClassName IOrganizationDao
 * @Description 组织机构DAO
 * @author wyn
 * @Date 2017年12月2日 下午10:32:29
 * @version 1.0.0
 */
@MapperScan
public interface IOrganizationDao extends IBaseDao<Organization> {
    
    /**
     * 
     * @Description 根据上级ID获取下级组织
     * @param pId
     * @return
     */            
    List<Organization> selectDatasByPId(String pId);

    /**
     * 
     * @Description 获取全部可用组织机构
     * @return
     */
    List<Organization> selectAll();
    
    
    
}