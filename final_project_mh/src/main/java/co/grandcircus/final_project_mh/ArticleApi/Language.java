package co.grandcircus.final_project_mh.ArticleApi;

public class Language {
	
	/*
	"language": {
	"name": "English",
	"isoCode": "eng",
	"isoCode2": "en"
	},
	*/
	
	private String name;
	private String isoCode;
	private String isoCode2;
	
	//@return GET name
	public String getName() {
		return name;
	}
	
	//@param SET name
	public void setName(String name) {
		this.name = name;
	}
	
	//@return GET isoCode
	public String getIsoCode() {
		return isoCode;
	}
	
	//@param SET isoCode
	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}
	
	//@return GET isoCode2
	public String getIsoCode2() {
		return isoCode2;
	}
	
	//@param SET isoCode2
	public void setIsoCode2(String isoCode2) {
		this.isoCode2 = isoCode2;
	}

	@Override
	public String toString() {
		return "Language [name=" + name + ", isoCode=" + isoCode + ", isoCode2=" + isoCode2 + "]";
	}
	
	


}
