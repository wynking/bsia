package cn.com.pansky.otp5.report.linechart;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class TestFactory {

    public static Collection<TestReport> getTestReports() {
        Collection<TestReport> testReports = new Vector<TestReport>();
        List<TestBean> testBeans = new ArrayList<TestBean>();
        TestBean tb1 = new TestBean();
        tb1.setCat("1");
        tb1.setValue(1);
        testBeans.add(tb1);
        TestBean tb2 = new TestBean();
        tb2.setCat("2");
        tb2.setValue(2);
        testBeans.add(tb2);
        JRBeanCollectionDataSource resultsList = new JRBeanCollectionDataSource(testBeans);
        TestReport tr = new TestReport();
        tr.setResultsList(resultsList);
        testReports.add(tr);
        return testReports;
    }
}
