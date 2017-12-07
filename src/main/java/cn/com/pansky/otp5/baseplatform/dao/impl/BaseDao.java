/*package cn.com.pansky.otp5.baseplatform.dao.impl;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import cn.com.pansky.otp5.baseplatform.dao.IBaseDao;

public class BaseDao<T> extends SqlSessionDaoSupport  implements IBaseDao<T>{
	
	@Resource
	protected SqlSessionFactory sqlSessionFactory;
	
	private SqlSessionDaoSupport sqlSessionDaoSupport;
	
	public SqlSessionDaoSupport getSqlSessionDaoSupport() {
		return sqlSessionDaoSupport;
	}

	public void setSqlSessionDaoSupport(SqlSessionDaoSupport sqlSessionDaoSupport) {
		this.sqlSessionDaoSupport = sqlSessionDaoSupport;
	}
	
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public T add(T t) {
		getSqlSessionFactory().openSession().insert(t.getClass().getName()+".insert",t);
		return t;
	}

}
*/