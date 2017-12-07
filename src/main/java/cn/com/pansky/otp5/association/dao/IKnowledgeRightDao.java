package cn.com.pansky.otp5.association.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import cn.com.pansky.otp5.association.dao.po.IndustryRelate;
import cn.com.pansky.otp5.association.dao.po.KnowledgeRight;
import cn.com.pansky.otp5.baseplatform.dao.IBaseDao;

@MapperScan
public interface IKnowledgeRightDao extends IBaseDao<KnowledgeRight> {
    /**
     * 
     * @Description 根据企业id删除知识产权信息
     * @param eId
     */
    void updateEnableByEId(String eId,String enable);

    List<KnowledgeRight> selectAll();
}