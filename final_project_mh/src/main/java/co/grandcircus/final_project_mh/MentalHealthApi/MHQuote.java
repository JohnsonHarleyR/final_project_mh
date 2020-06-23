package co.grandcircus.final_project_mh.MentalHealthApi;

//Created by Harley

public class MHQuote {
	
	private Long id;
	private String quote;
	private String author;
	private String tag1;
	private String tag2;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuote() {
		return quote;
	}
	public void setQuote(String quote) {
		this.quote = quote;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTag1() {
		return tag1;
	}
	public void setTag1(String tag1) {
		this.tag1 = tag1;
	}
	public String getTag2() {
		return tag2;
	}
	public void setTag2(String tag2) {
		this.tag2 = tag2;
	}
	
	
	@Override
	public String toString() {
		return "MHQuote [id=" + id + ", quote=" + quote + ", author=" + author + ", tag1=" + tag1 + ", tag2=" + tag2
				+ "]";
	}
	
	
	

}
