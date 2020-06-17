package co.grandcircus.final_project_mh.QuoteApi;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//I am storing the daily quote in a database to help avoid over usage
//I'm sure there are other ways to prevent the page going out, but I'm sure this
//will work fine lol

@Entity
@Table(name = "quote_of_the_day")
public class QuoteOfTheDay implements Comparable<QuoteOfTheDay> {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date datetime;
	private String quote;
	private String author;
	
	public QuoteOfTheDay() {}
	
	public QuoteOfTheDay(Date datetime, String quote, String auther) {
		this.datetime = datetime;
		this.quote = quote;
		this.author = author;
	}
	

	
	public Long getId() {
		return id;
	}






	public void setId(Long id) {
		this.id = id;
	}






	public Date getDatetime() {
		return datetime;
	}






	public void setDatetime(Date datetime) {
		this.datetime = datetime;
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


	



	@Override
	public String toString() {
		return "QuoteOfTheDay [id=" + id + ", datetime=" + datetime + ", quote=" + quote + ", author=" + author + "]";
	}






	//Compare by date. If they're the same, compare by id order.
	//(Guess you could probably just compare by id but oh well. This is
	//more error proof.)
	@Override
	public int compareTo(QuoteOfTheDay o) {
		if (datetime == o.getDatetime()) {
			return id.compareTo(o.getId());
		} else {
			return datetime.compareTo(o.getDatetime());
		}
		
	}

	
}
