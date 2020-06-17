package co.grandcircus.final_project_mh.ArticleApi;


/*
 * "pagination": {
"total": 1,
"count": 1,
"max": 100,
"offset": 0,
"pageNum": 1,
"totalPages": 1,
"sort": "-datepublished",
"previousUrl": "",
"currentUrl": "https://tools.cdc.gov/api/v2/resources/media?topic=depression",
"nextUrl": ""
 */

public class Pagination {
	
	private Integer total;
	private Integer count;
	private Integer max;
	private Integer offset;
	private Integer pageNum;
	private Integer totalPages;
	private String sort;
	private String previousUrl;
	private String currentUrl;
	private String nextUrl;
	
	//@return GET total
	public Integer getTotal() {
		return total;
	}
	
	//@param SET total
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	//@return GET count
	public Integer getCount() {
		return count;
	}
	
	//@param SET count
	public void setCount(Integer count) {
		this.count = count;
	}
	
	//@return GET max
	public Integer getMax() {
		return max;
	}
	
	//@param SET max
	public void setMax(Integer max) {
		this.max = max;
	}
	
	//@return GET offset
	public Integer getOffset() {
		return offset;
	}
	
	//@param SET offset
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	
	//@return GET pageNum
	public Integer getPageNum() {
		return pageNum;
	}
	
	//@param SET pageNum
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	
	//@return GET totalPages
	public Integer getTotalPages() {
		return totalPages;
	}
	
	//@param SET totalPages
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	
	//@return GET sort
	public String getSort() {
		return sort;
	}
	
	//@param SET sort
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	//@return GET previousUrl
	public String getPreviousUrl() {
		return previousUrl;
	}
	
	//@param SET previousUrl
	public void setPreviousUrl(String previousUrl) {
		this.previousUrl = previousUrl;
	}
	
	//@return GET currentUrl
	public String getCurrentUrl() {
		return currentUrl;
	}
	
	//@param SET currentUrl
	public void setCurrentUrl(String currentUrl) {
		this.currentUrl = currentUrl;
	}
	
	//@return GET nextUrl
	public String getNextUrl() {
		return nextUrl;
	}
	
	//@param SET nextUrl
	public void setNextUrl(String nextUrl) {
		this.nextUrl = nextUrl;
	}

	@Override
	public String toString() {
		return "Pagination [total=" + total + ", count=" + count + ", max=" + max + ", offset=" + offset + ", pageNum="
				+ pageNum + ", totalPages=" + totalPages + ", sort=" + sort + ", previousUrl=" + previousUrl
				+ ", currentUrl=" + currentUrl + ", nextUrl=" + nextUrl + "]";
	}
	
	
	

}
