package cn.com.pansky.otp5.baseplatform.dao.myDataSource;

import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import cn.com.pansky.otp5.common.PorpertiesConfigurer;

@Intercepts({
@Signature(type = Executor.class, method = "update", args = {
        MappedStatement.class, Object.class }),
@Signature(type = Executor.class, method = "query", args = {
        MappedStatement.class, Object.class, RowBounds.class,
        ResultHandler.class }) })
public class DynamicPlugin implements Interceptor {

   
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        
        boolean mutiDB = Boolean.parseBoolean(PorpertiesConfigurer.getContextProperty("mutiDB").toString()) ;
        
        if(mutiDB){
            Object[] objects = invocation.getArgs();
            MappedStatement ms = (MappedStatement) objects[0];
            System.err.println("ms.getId()============>>>>" + ms.getId());
             BoundSql boundSql = ms.getSqlSource().getBoundSql(objects[1]);   
               String sql = boundSql.getSql();
                System.err.println("sql============>>>>" + sql);

                String sqlType = sql.substring(0,sql.indexOf(" "));
                System.err.println("sqlType============>>>>" + sqlType);
                if("insert".equalsIgnoreCase(sqlType)
                        ||"delete".equalsIgnoreCase(sqlType)
                        ||"update".equalsIgnoreCase(sqlType)
                        ){
                    System.err.println("数据库==================设置=======================>>>>>" + DataSourceType.SLAVE);
                    DataSourceSwitch.setDataSourceType(DataSourceType.SLAVE);
                    //DynamicDataSourceHolder.putDataSource(DynamicDataSourceEnum.WRITE.name());
                }else{
                    DataSourceSwitch.setDataSourceType(DataSourceType.MASTER);
                    System.err.println("数据库==================设置=======================>>>>>" + DataSourceType.MASTER);
                }
            
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        System.out.println("Executor===============>>>" + (target instanceof Executor));
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
        //
    }
}