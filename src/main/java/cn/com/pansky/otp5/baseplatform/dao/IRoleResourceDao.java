package cn.com.pansky.otp5.baseplatform.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import cn.com.pansky.otp5.baseplatform.dao.po.RoleResource;

@MapperScan
public interface IRoleResourceDao extends IBaseDao<RoleResource>{
    
    /**
     * 
     * @Description 根据角色ID删除角色下的资源
     * @param roleId
     */
    void deleteByRoleId(String roleId);
    
    /**
     * 
     * @Description 根据角色ID查询角色下的资源
     * @param roleId
     * @return
     */
    List<RoleResource> selectByRoleId(String roleId);
    
}