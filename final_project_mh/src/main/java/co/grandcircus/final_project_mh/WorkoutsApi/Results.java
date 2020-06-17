package co.grandcircus.final_project_mh.WorkoutsApi;

import java.util.List;

public class Results {
	private Integer id;
	private String name;
	private Integer category;
	private Integer language;
	private String description;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public Integer getLanguage() {
		return language;
	}
	public void setLanguage(Integer language) {
		this.language = language;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Results [id=" + id + ", name=" + name + ", category=" + category + ", language=" + language
				+ ", description=" + description +"]";
	}
	
}
