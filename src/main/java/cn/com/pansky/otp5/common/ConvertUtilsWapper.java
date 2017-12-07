package cn.com.pansky.otp5.common;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.ShortConverter;

public class ConvertUtilsWapper {

	public static void register() {
//	    ConvertUtilsBean convertUtils = new ConvertUtilsBean();// java自动转换的工具类
//	    DateLocaleConverter dateConverter = new DateLocaleConverter();// 实力㈠个日期转换类
//	    convertUtils.register(dateConverter, Date.class);// 注册㈠个日期类
//	    convertUtils.register(dateConverter, String.class);// 注册㈠个字符类
	    strToDate();
		dateToStr();
		 ConvertUtils.register(new LongConverter(null), Long.class);  
		    ConvertUtils.register(new ShortConverter(null), Short.class);  
		    ConvertUtils.register(new IntegerConverter(null), Integer.class);  
		    ConvertUtils.register(new DoubleConverter(null), Double.class);  
		    ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class); 
//		doubleToStr();
	}

    
    private static void strToDate(){ConvertUtils.register(new Converter() {
        @SuppressWarnings("rawtypes")
        @Override
        public Object convert(Class arg0, Object arg1) {
//            System.out.println("注册字符串转换为date类型转换器");
            if (arg1 == null) {
                return null;
            }
            if (!(arg1 instanceof String)) {
                System.out.println("arg1===============>>>>" + arg1);
                throw new ConversionException("只支持字符串转换 !");
            }

            String str = (String) arg1;
            if (str.trim().equals("")) {
                return null;
            }

            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            try {
//                System.out.println("strToDate================>>>>" + str);
                return sd.parse(str);
            } catch (ParseException e) {
                sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                try {
                    return sd.parse(str);
                } catch (ParseException e1) {
                    e1.printStackTrace();
                    throw new RuntimeException(e);
                }
            }

        }

    }, Date.class);
   }
    
    private static void dateToStr(){
        ConvertUtils.register(new Converter() {
            @SuppressWarnings("rawtypes")
            @Override
            public Object convert(Class arg0, Object arg1) {
//              System.out.println("arg1=============================>>>>" + arg1);
                if(arg1 instanceof Date){
//                    System.out.println("dateToStr================>>>>DDDDDDDDDDDD");
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
                    String str=sdf.format(arg1); 
                    return str;
                }else if(arg1 instanceof Integer ){
//                  System.out.println("dateToStr=========>>>>" + arg1);
                  return String.valueOf(arg1);
                }else if(arg1 instanceof Double ){
//                  System.out.println("doubleToStr================>>>>" + arg1);
                    return String.valueOf(arg1);
                }else if(arg1 instanceof Long ){
//                  System.out.println("doubleToStr================>>>>" + arg1);
                    return String.valueOf(arg1);
                }else if(arg1 instanceof Float ){
//                  System.out.println("doubleToStr================>>>>" + arg1);
                    return String.valueOf(arg1);
                }
                return arg1;
            
            }

        }, String.class);
    }
    
    

    
    
    
    
	

}
