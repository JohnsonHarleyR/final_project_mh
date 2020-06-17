package co.grandcircus.final_project_mh.ArticleApi;

import java.util.Arrays;


public class Meta {
	
	private Integer status;
	private String[] message;
	private ResultSet resultSet;
	private Pagination pagination;
	
	//@return GET status
	public Integer getStatus() {
		return status;
	}
	
	//@param SET status
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	//@return GET message
	public String[] getMessage() {
		return message;
	}
	
	//@param SET message
	public void setMessage(String[] message) {
		this.message = message;
	}
	
	//@return GET resultSet
	public ResultSet getResultSet() {
		return resultSet;
	}
	
	//@param SET resultSet
	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}
	
	//@return GET pagination
	public Pagination getPagination() {
		return pagination;
	}
	
	//@param SET pagination
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	
	@Override
	public String toString() {
		return "Meta [status=" + status + ", message=" + Arrays.toString(message) + ", resultSet=" + resultSet
				+ ", pagination=" + pagination + "]";
	}
	
	
	
	

}
