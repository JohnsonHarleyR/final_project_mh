package co.grandcircus.final_project_mh.ArticleApi;

public class ResultSet {
	
	private String id;

	
	//@return GET id
	public String getId() {
		return id;
	}

	
	//@param SET id
	public void setId(String id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "ResultSet [id=" + id + "]";
	}
	
	

}
