package cn.com.pansky.otp5.baseplatform.dao.myDataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;  
public class DataSources extends AbstractRoutingDataSource  
{  
    @Override  
    protected Object determineCurrentLookupKey()  
    {  
        
        
        
        System.err.println("数据库==================获取=======================>>>>>" + DataSourceSwitch.getDataSourceType());
        
        return DataSourceSwitch.getDataSourceType();  
    }  
}  
