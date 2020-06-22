package HarleyJohnson.HealingApi.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "quotes_and_animal")
public class QuoteAndAnimal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String quote;
	private String author;
	private String tag1;
	private String tag2;
	@Column(name = "animal_url")
	private String animalUrl;
	private String type;
	
	
	public QuoteAndAnimal() {}
	
	
	public QuoteAndAnimal(String quote, String author, String tag1, String tag2, String animalUrl, String type) {
		super();
		this.quote = quote;
		this.author = author;
		this.tag1 = tag1;
		this.tag2 = tag2;
		this.animalUrl = animalUrl;
		this.type = type;
	}
	
	
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
	public String getAnimalUrl() {
		return animalUrl;
	}
	public void setAnimalUrl(String animalUrl) {
		this.animalUrl = animalUrl;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	@Override
	public String toString() {
		return "QuoteAndAnimal [id=" + id + ", quote=" + quote + ", author=" + author + ", tag1=" + tag1 + ", tag2="
				+ tag2 + ", animalUrl=" + animalUrl + ", type=" + type + "]";
	}
	
	
}
