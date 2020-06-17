package co.grandcircus.final_project_mh.QuoteApi;

public class QOD {
	
	private Success success;
	private Contents contents;
	private String baseurl;
	private Copyright copyright;
	
	//@return GET success
	public Success getSuccess() {
		return success;
	}
	
	//@param SET success
	public void setSuccess(Success success) {
		this.success = success;
	}
	
	//@return GET contents
	public Contents getContents() {
		return contents;
	}
	
	//@param SET contents
	public void setContents(Contents contents) {
		this.contents = contents;
	}
	
	//@return GET baseurl
	public String getBaseurl() {
		return baseurl;
	}
	
	//@param SET baseurl
	public void setBaseurl(String baseurl) {
		this.baseurl = baseurl;
	}
	
	//@return GET copyright
	public Copyright getCopyright() {
		return copyright;
	}
	
	//@param SET copyright
	public void setCopyright(Copyright copyright) {
		this.copyright = copyright;
	}

	@Override
	public String toString() {
		return "QOD [success=" + success + ", contents=" + contents + ", baseurl=" + baseurl + ", copyright="
				+ copyright + "]";
	}
	
	

}
