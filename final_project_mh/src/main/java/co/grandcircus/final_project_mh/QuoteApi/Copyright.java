package co.grandcircus.final_project_mh.QuoteApi;

public class Copyright {
	
	private Integer year;
	private String url;
	
	//@return GET year
	public Integer getYear() {
		return year;
	}
	
	//@param SET year
	public void setYear(Integer year) {
		this.year = year;
	}
	
	//@return GET url
	public String getUrl() {
		return url;
	}
	
	//@param SET url
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Copyright [year=" + year + ", url=" + url + "]";
	}
	
	

}
