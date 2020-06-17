package co.grandcircus.final_project_mh.QuoteApi;

public class Success {
	
	private Integer total;

	
	//@return GET total
	public Integer getTotal() {
		return total;
	}

	
	//@param SET total
	public void setTotal(Integer total) {
		this.total = total;
	}


	@Override
	public String toString() {
		return "Success [total=" + total + "]";
	}
	
	
	

}
