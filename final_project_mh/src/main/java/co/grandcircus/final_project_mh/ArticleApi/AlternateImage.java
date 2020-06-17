package co.grandcircus.final_project_mh.ArticleApi;

/*
 * "id": 543600,
"name": "thumbnail",
"width": 155,
"height": 84,
"url": "https://tools.cdc.gov/api/v2/resources/links/543600.png",
"type": "thumbnail"
 */


public class AlternateImage {
	
	private Integer id;
	private String name;
	private Integer width;
	private Integer height;
	private String url;
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
	
	//@return GET width
	public Integer getWidth() {
		return width;
	}
	
	//@param SET width
	public void setWidth(Integer width) {
		this.width = width;
	}
	
	//@return GET height
	public Integer getHeight() {
		return height;
	}
	
	//@param SET height
	public void setHeight(Integer height) {
		this.height = height;
	}
	
	//@return GET url
	public String getUrl() {
		return url;
	}
	
	//@param SET url
	public void setUrl(String url) {
		this.url = url;
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
		return "AlternateImage [id=" + id + ", name=" + name + ", width=" + width + ", height=" + height + ", url="
				+ url + ", type=" + type + "]";
	}
	
	
	

}
