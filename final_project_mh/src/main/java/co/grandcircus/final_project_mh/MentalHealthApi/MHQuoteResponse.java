package co.grandcircus.final_project_mh.MentalHealthApi;

import java.util.Arrays;

//Created by Harley
public class MHQuoteResponse {
	
	private MHQuote[] quotes;

	public MHQuote[] getQuotes() {
		return quotes;
	}

	public void setQuotes(MHQuote[] quotes) {
		this.quotes = quotes;
	}

	@Override
	public String toString() {
		return "MHQuoteResponse [quotes=" + Arrays.toString(quotes) + "]";
	}
	
	

}
