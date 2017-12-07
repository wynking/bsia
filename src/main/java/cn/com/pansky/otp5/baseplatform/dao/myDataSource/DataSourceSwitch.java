package cn.com.pansky.otp5.baseplatform.dao.myDataSource;


public class DataSourceSwitch {
    
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();  
      
    public static void setDataSourceType(String dataSourceType)  
    {  
        contextHolder.set(dataSourceType);  
    }  
      
    public static String getDataSourceType()  
    {  
        return contextHolder.get();  
    }  
      
    public static void clearDataSourceType()  
    {  
        contextHolder.remove();  
    }  
}
