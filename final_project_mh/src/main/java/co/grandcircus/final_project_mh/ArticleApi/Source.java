package co.grandcircus.final_project_mh.ArticleApi;

/*
 * "source": {
"name": "Centers for Disease Control and Prevention",
"acronym": "CDC",
"websiteUrl": "",
"largeLogoUrl": "",
"smallLogoUrl": ""
},

 */



public class Source {
	
	private String name;
	private String acronym;
	
	//@return GET name
	public String getName() {
		return name;
	}
	
	//@param SET name
	public void setName(String name) {
		this.name = name;
	}
	
	//@return GET acronym
	public String getAcronym() {
		return acronym;
	}
	
	//@param SET acronym
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	@Override
	public String toString() {
		return "Source [name=" + name + ", acronym=" + acronym + "]";
	}
	
	
	

}
