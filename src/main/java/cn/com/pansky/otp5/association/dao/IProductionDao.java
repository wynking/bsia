package cn.com.pansky.otp5.association.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import cn.com.pansky.otp5.association.dao.po.KnowledgeRight;
import cn.com.pansky.otp5.association.dao.po.Production;
import cn.com.pansky.otp5.association.dao.po.TechRelate;
import cn.com.pansky.otp5.baseplatform.dao.IBaseDao;

@MapperScan
public interface IProductionDao extends IBaseDao<Production> {
    /**
     * 
     * @Description 根据企业id删除产品信息
     * @param eId
     */
    void updateEnableByEId(String eId,String enable);

    List<Production> selectAll();
}