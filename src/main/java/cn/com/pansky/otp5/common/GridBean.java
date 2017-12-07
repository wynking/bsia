package cn.com.pansky.otp5.common;

import java.util.List;

/**
 * 
 * @ClassName GridBean
 * @Description 用于存储和传递表格数据
 * @author wyn
 * @Date 2017年9月7日 上午11:29:12
 * @version 1.0.0
 * @param <T>
 */
public class GridBean<T> {

    private long total;
    
    private List<T> rows;

    public long getTotal() {
        return total;
    }

    
    public void setTotal(long total) {
        this.total = total;
    }

    
    public List<T> getRows() {
        return rows;
    }

    
    public void setRows(List<T> rows) {
        this.rows = rows;
    }
    
    
}
