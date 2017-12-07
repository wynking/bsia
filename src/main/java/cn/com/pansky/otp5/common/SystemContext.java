package cn.com.pansky.otp5.common;

/**
 * 用来传递列表对象的ThreadLocal数据
 * @author wyn
 *
 */
public class SystemContext {
	/**
	 * 分页大小
	 */
	private static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();
	/**
	 * 分页的起始页
	 */
	private static ThreadLocal<Integer> pageOffset = new ThreadLocal<Integer>();
	/**
	 * 当前第几页
	 */
	private static ThreadLocal<Integer> current = new ThreadLocal<Integer>();
	/**
	 * 列表的排序字段
	 */
	private static ThreadLocal<String> sort = new ThreadLocal<String>();
	/**
	 * 列表的排序方式
	 */
	private static ThreadLocal<String> order = new ThreadLocal<String>();
    
    private static ThreadLocal<String> realPath = new ThreadLocal<String>();
    
    private static ThreadLocal<String> userId = new ThreadLocal<String>();
    
    /**
     * 企业数据导入导出时使用
     */
    private static ThreadLocal<String> format = new ThreadLocal<String>();
    
	

    public static String getFormat() {
        return format.get();
    }
    public static void setFormat(String _format) {
        SystemContext.format.set(_format);
    }

    public static String getRealPath() {
        return realPath.get();
    }
    public static void setRealPath(String _realPath) {
        SystemContext.realPath.set(_realPath);
    }
    
    public static String getUserId() {
        return userId.get();
    }
    
    public static void setUserId(String userId) {
        SystemContext.userId.set(userId);;
    }
    public static Integer getPageSize() {
		return pageSize.get();
	}
	public static void setPageSize(Integer _pageSize) {
		pageSize.set(_pageSize);
	}
	public static Integer getPageOffset() {
		return pageOffset.get();
	}
	public static void setPageOffset(Integer _pageOffset) {
		pageOffset.set(_pageOffset);
	}
	
	public static Integer getCurrent() {
		return current.get();
	}
	public static void setCurrent(Integer _current) {
		current.set(_current);
	}
	public static String getSort() {
		return sort.get();
	}
	public static void setSort(String _sort) {
		SystemContext.sort.set(_sort);
	}
	public static String getOrder() {
		return order.get();
	}
	public static void setOrder(String _order) {
		SystemContext.order.set(_order);
	}
	
	public static void removePageSize() {
		pageSize.remove();
	}
	
	public static void removePageOffset() {
		pageOffset.remove();
	}
	
	public static void removeSort() {
		sort.remove();
	}
	
	public static void removeOrder() {
		order.remove();
	}

    public static void removeRealPath() {
        realPath.remove();
    }

    public static void removeUserId() {
        userId.remove();
    }
    
}
