package cn.com.pansky.otp5.baseplatform.dao.myDataSource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;

import cn.com.pansky.otp5.common.PorpertiesConfigurer;

public class DynamicDataSourceTransactionManager extends DataSourceTransactionManager {

    /**
     * 只读事务到读库，读写事务到写库
     * @param transaction
     * @param definition
     */
    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) {
        
        boolean mutiDB = Boolean.parseBoolean(PorpertiesConfigurer.getContextProperty("mutiDB").toString()) ;
        if(mutiDB){
            boolean readOnly = definition.isReadOnly();
            System.err.println("readOnly==============>>>" + readOnly);
            if(readOnly) {
                DataSourceSwitch.setDataSourceType(DataSourceType.SLAVE);
            } else {
                DataSourceSwitch.setDataSourceType(DataSourceType.MASTER);
            }
        }
        super.doBegin(transaction, definition);
    }

    /**
     * 清理本地线程的数据源
     * @param transaction
     */
    @Override
    protected void doCleanupAfterCompletion(Object transaction) {
        super.doCleanupAfterCompletion(transaction);
        System.err.println("清理本地线程的数据源================>>>>>" + DataSourceSwitch.getDataSourceType());
        DataSourceSwitch.clearDataSourceType();
        
    }
}