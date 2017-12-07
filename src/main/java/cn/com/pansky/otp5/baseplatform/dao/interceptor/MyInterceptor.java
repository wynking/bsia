package cn.com.pansky.otp5.baseplatform.dao.interceptor;
import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
@Intercepts( {
    @Signature(method = "query", type = Executor.class, args = {
           MappedStatement.class, Object.class, RowBounds.class,
           ResultHandler.class }),
    @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class }) })
public class MyInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
//        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
      /*  StatementHandler statement = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statement.getBoundSql();
        Object parameterObject = boundSql.getParameterObject();
//此处的parameterObject为被拦截DAO方法的参数，比如这个Dao的参数可以为一个page对象，对象属性包含当前面，每页显示的行数，总页数，总行数，当前页的结果集（一般为一个List），以及一个集合（前端传递过来的，查询条件集合）
        System.err.println(parameterObject);
        String sql = boundSql.getSql();
//这里获得原来sql后，就可以进行改造了，处理为查询总数据的即可。
        System.err.println(sql.substring(0,sql.indexOf(" ")));*/
        
       
        
        Object result = invocation.proceed();
        System.out.println("Invocation.proceed()");
        return result;
    }

    @Override
    public Object plugin(Object target) {
//        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        // TODO Auto-generated method stub
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
//        System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
//        String prop1 = properties.getProperty("prop1");
//        String prop2 = properties.getProperty("prop2");
//        System.out.println(prop1 + "------" + prop2);
    }
}