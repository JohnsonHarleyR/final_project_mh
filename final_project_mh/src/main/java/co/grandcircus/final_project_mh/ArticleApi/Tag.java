package co.grandcircus.final_project_mh.ArticleApi;

/*
 * {
"id": 5496,
"name": "Data & Stats",
"language": "English",
"type": "Topic"
},
 */


public class Tag {
	
	private Integer id;
	private String name;
	private String type;
	
	//@return GET id
	public Integer getId() {
		return id;
	}
	
	//@param SET id
	public void setId(Integer id) {
		this.id = id;
	}
	
	//@return GET name
	public String getName() {
		return name;
	}
	
	//@param SET name
	public void setName(String name) {
		this.name = name;
	}
	
	//@return GET type
	public String getType() {
		return type;
	}
	
	//@param SET type
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + ", type=" + type + "]";
	}
	
	
	
	

}
