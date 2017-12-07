package cn.com.pansky.otp5.common;

import java.util.List;

/**
 * 
 * @author wyn
 *
 */
public class Pager<T> {
	
	/*
	 * 每页记录数
	 */
	private int pageSize=10;	

	/*
	 * 当前页数
	 */
	private int pageIndex=0;	
	
	/*
	 * 查询起始条数
	 */
	private int pageOffset=0;
	
	/*
	 * 总记录数
	 */
	private int totalRecord = 0;
	
	/*
	 * 总页数
	 */
	private int	totalPage;
	
	/*
	 * 每页的数据
	 */
	private List<T> rows;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getPageOffset() {
		return pageOffset;
	}

	public void setPageOffset(int pageOffset) {
		this.pageOffset = pageOffset;
	}
	
}
