package cn.com.pansky.otp5.common;

/**
 * 
 * @ClassName ResultBean
 * @Description 请求返回对象
 * @author wyn
 * @Date 2017年9月8日 下午5:40:21
 * @version 1.0.0
 */
public class ResultBean {

    private int flag;
    
    private String msg;
    
    private Object data;

    
    public int getFlag() {
        return flag;
    }

    
    public void setFlag(int flag) {
        this.flag = flag;
    }

    
    public String getMsg() {
        return msg;
    }

    
    public void setMsg(String msg) {
        this.msg = msg;
    }

    
    public Object getData() {
        return data;
    }

    
    public void setData(Object data) {
        this.data = data;
    }
    
    public void isFail(){
        this.flag=0;
    }
    
    public void isSuccess(){
        this.flag=1;
    }
    
    
}
