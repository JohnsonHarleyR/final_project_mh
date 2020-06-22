package HarleyJohnson.HealingApi.entity;

import java.util.List;

public class QuoteResponse {
	
	private List<Quote> quotes;
	
	
	public QuoteResponse() {};

	
	public QuoteResponse(List<Quote> quotes) {
		super();
		this.quotes = quotes;
	}


	//@return GET quotes
	public List<Quote> getQuotes() {
		return quotes;
	}

	
	//@param SET quotes
	public void setQuotes(List<Quote> quotes) {
		this.quotes = quotes;
	}


	@Override
	public String toString() {
		return "QuoteResponse [quotes=" + quotes + "]";
	}
	
	

}
