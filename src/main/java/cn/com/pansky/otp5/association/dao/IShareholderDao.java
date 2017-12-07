package cn.com.pansky.otp5.association.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import cn.com.pansky.otp5.association.dao.po.Shareholder;
import cn.com.pansky.otp5.association.dao.po.TechRelate;
import cn.com.pansky.otp5.baseplatform.dao.IBaseDao;

@MapperScan
public interface IShareholderDao extends IBaseDao<Shareholder> {
    /**
     * 
     * @Description 根据企业id删除股权信息
     * @param eId
     */
    void updateEnableByEId(String eId,String enable);
    
    List<Shareholder> selectAll();
}