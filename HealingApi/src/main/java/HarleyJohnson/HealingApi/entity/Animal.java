package HarleyJohnson.HealingApi.entity;

public class Animal {
	
	private long id;
	private String url;
	private String type;
	
	
	public Animal() {}
	
	public Animal(long id, String url, String type) {
		super();
		this.id = id;
		this.url = url;
		this.type = type;
	}

	//@return GET id
	public long getId() {
		return id;
	}
	
	//@param SET id
	public void setId(long id) {
		this.id = id;
	}
	
	//@return GET url
	public String getUrl() {
		return url;
	}
	
	//@param SET url
	public void setUrl(String url) {
		this.url = url;
	}
	
	//@return GET tag
	public String getType() {
		return type;
	}
	
	//@param SET tag
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Animal [id=" + id + ", url=" + url + ", type=" + type + "]";
	}
	

}
