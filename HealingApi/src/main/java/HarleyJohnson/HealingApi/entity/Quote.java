package HarleyJohnson.HealingApi.entity;

public class Quote {
	
	private long id;
	private String quote;
	private String author;
	private String tag1;
	private String tag2;
	
	
	
	public Quote() {}
	
	
	public Quote(long id, String quote, String author, String tag1, String tag2) {
		super();
		this.id = id;
		this.quote = quote;
		this.author = author;
		this.tag1 = tag1;
		this.tag2 = tag2;
	}

	//@return GET id
	public long getId() {
		return id;
	}
	
	//@param SET id
	public void setId(long id) {
		this.id = id;
	}
	
	//@return GET quote
	public String getQuote() {
		return quote;
	}
	
	//@param SET quote
	public void setQuote(String quote) {
		this.quote = quote;
	}
	
	//@return GET author
	public String getAuthor() {
		return author;
	}
	
	//@param SET author
	public void setAuthor(String author) {
		this.author = author;
	}
	
	//@return GET tag1
	public String getTag1() {
		return tag1;
	}
	
	//@param SET tag1
	public void setTag1(String tag1) {
		this.tag1 = tag1;
	}
	
	//@return GET tag2
	public String getTag2() {
		return tag2;
	}
	
	//@param SET tag2
	public void setTag2(String tag2) {
		this.tag2 = tag2;
	}

	@Override
	public String toString() {
		return "Quote [id=" + id + ", quote=" + quote + ", author=" + author + ", tag1=" + tag1 + ", tag2=" + tag2
				+ "]";
	}
	
	

}
