package co.grandcircus.final_project_mh.QuoteApi;

import java.util.Arrays;

public class Quote {
	
	private String quote;
	private Integer length;
	private String author;
	//private String[] tags;
	private String category;
	private String language;
	private String date;
	private String permalink;
	private String id;
	private String background;
	private String title;
	
	//@return GET quote
	public String getQuote() {
		return quote;
	}
	
	//@param SET quote
	public void setQuote(String quote) {
		this.quote = quote;
	}
	
	//@return GET length
	public Integer getLength() {
		return length;
	}
	
	//@param SET length
	public void setLength(Integer length) {
		this.length = length;
	}
	
	//@return GET author
	public String getAuthor() {
		return author;
	}
	
	//@param SET author
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/*
	//@return GET tags
	public String[] getTags() {
		return tags;
	}
	
	//@param SET tags
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	*/
	
	//@return GET category
	public String getCategory() {
		return category;
	}
	
	//@param SET category
	public void setCategory(String category) {
		this.category = category;
	}
	
	//@return GET language
	public String getLanguage() {
		return language;
	}
	
	//@param SET language
	public void setLanguage(String language) {
		this.language = language;
	}
	
	//@return GET date
	public String getDate() {
		return date;
	}
	
	//@param SET date
	public void setDate(String date) {
		this.date = date;
	}
	
	//@return GET permalink
	public String getPermalink() {
		return permalink;
	}
	
	//@param SET permalink
	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}
	
	//@return GET id
	public String getId() {
		return id;
	}
	
	//@param SET id
	public void setId(String id) {
		this.id = id;
	}
	
	//@return GET background
	public String getBackground() {
		return background;
	}
	
	//@param SET background
	public void setBackground(String background) {
		this.background = background;
	}
	
	//@return GET title
	public String getTitle() {
		return title;
	}
	
	//@param SET title
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Quote [quote=" + quote + ", length=" + length + ", author=" + author + ", category=" + category
				+ ", language=" + language + ", date=" + date + ", permalink=" + permalink + ", id=" + id
				+ ", background=" + background + ", title=" + title + "]";
	}

	/*
	@Override
	public String toString() {
		return "Quote [quote=" + quote + ", length=" + length + ", author=" + author + ", tags=" + Arrays.toString(tags)
				+ ", category=" + category + ", language=" + language + ", date=" + date + ", permalink=" + permalink
				+ ", id=" + id + ", background=" + background + ", title=" + title + "]";
	}
	*/
	
	
	
	

}