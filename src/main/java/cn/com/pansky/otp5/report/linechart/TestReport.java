package cn.com.pansky.otp5.report.linechart;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;  

public class TestReport {

    JRBeanCollectionDataSource resultsList;

    public TestReport() {
    }

    public JRBeanCollectionDataSource getResultsList(){  
        return resultsList;  
    }

    public void setResultsList(JRBeanCollectionDataSource resultsList) {
        this.resultsList = resultsList;
    }
}
