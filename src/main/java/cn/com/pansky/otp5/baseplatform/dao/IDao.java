/*package cn.com.pansky.otp5.baseplatform.dao;

import java.sql.SQLException;
import java.util.List;

import cn.com.pansky.otp4.dao.mybatis.TableModel;

public interface IDao {
	public	final static	String	S_MODEL_NAME="$modelName";
	*//**
	 * 根据BEO的主键查出该对象
	 * @param beo 包含BEO的主键的BEO对象
	 * @throws Exception
	 * @return BEO
	 *//*
	public Object find(Object Beo) throws Exception;
	
	*//**
	 * 根据BEO的主键查出该对象
	 * @param beo 包含BEO主键的BEO对象
	 * @param returnLob 表示是否返回LOB字段
	 * @throws Exception
	 * @return BEO
	 *//*
	public Object find(Object Beo,boolean returnLob) throws Exception;

	*//**
	 * 把BEO对象插入到数据库，beo必须是 model中定义，主键必须存在
	 * @param beo 需要插入的BEO对象
	 * @throws Exception
	 * @return boolean 插入是否成功
	 *//*
	public boolean insert(Object Beo) throws Exception ;

	
	*//**
	 * 把BEO对象更新到数据库，beo必须是 model中定义，主键必须存在
	 * @param beo 需要更新的BEO对象
	 * @throws Exception
	 * @return boolean 更新是否成功
	 *//*
	public boolean update(Object beo, boolean updateIgnoreNull) throws Exception ;
	

	*//**
	 * 从数据库删除 BEO记录，beo对象中主键必须存在
	 * @param beo 包含BEO主键的BEO对象
	 * @throws Exception
	 * @return boolean 删除是否成功
	 *//*
	public boolean delete(Object Beo) throws Exception ;
	
	*//**
	 * 执行更新操作
	 * @param statementName，在mapper中定义的语句名
	 * @param parameterObject 要更新数据的对象(类型由mapper中定义决定)
	 * @throws Exception           
	 *//*
	public void executeUpdate(String statementName, Object parameterObject) throws Exception;
	
	
	*//**
	 * 查询操作
	 * @param statementName，在mapper中定义的语句名
	 * @param parameterObject 查询条件对象(类型由mapper中定义决定)
	 * @throws Exception
	 * @return List，记录是返回结果的类型           
	 *//*
	public List executeQuery(String statementName, Object parameterObject) throws Exception;
	
	*//**
	 * 分页查询
	 * @param statementName，在mapper中定义的语句名
	 * @param parameterObject 查询条件对象(类型由mapper中定义决定)
	 * @param pagination 分页对象属性
	 * @return Page对象，其中包含分页对象及List的结果集
	 * @throws Exception
	 *//*
	public Page<Object> executeQuery(String statementName, Object parameterObject, Pagination pagination) throws Exception;
	
	
	*//**
	 * 批量提交
	 * @param statementName，在mapper中定义的语句名
	 * @param listParams 是需要批量更新的List数据
	 *          
	 * @throws Exception           
	 *//*
	public void executeBatch(String statementName, List listParams) throws Exception;
	
	
	
	public TableModel getTableModel(Object obj) throws Exception ;
	
	public	boolean	updateLobs(Object obj) throws Exception;
	
	public	void	findLobs(Object obj)throws Exception;
	public	byte[] getLobAsByteArray(Object obj,String field) throws Exception;
}
*/