package HarleyJohnson.HealingApi;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import HarleyJohnson.HealingApi.dao.QuoteAndAnimalDao;
import HarleyJohnson.HealingApi.entity.Animal;
import HarleyJohnson.HealingApi.entity.AnimalResponse;
import HarleyJohnson.HealingApi.entity.Quote;
import HarleyJohnson.HealingApi.entity.QuoteAndAnimal;
import HarleyJohnson.HealingApi.entity.QuoteResponse;

@RestController
public class ApiController {
	
	@Autowired
	private QuoteAndAnimalDao repo;
	
	@Autowired
	HttpSession session;
	
	
	//Get list of all quotes
	@GetMapping("/quotes/all")
	QuoteResponse allQuotes() {
		
		//Create list of all
		List<QuoteAndAnimal> all = repo.findAll();
		//Start list of quotes
		List<Quote> quotes = new ArrayList<>();
		
		//loop through them all, creating quotes and adding to list
		for (QuoteAndAnimal item: all) {
			Quote quote = new Quote(item.getId(), item.getQuote(), item.getAuthor(),
					item.getTag1(), item.getTag2());
			quotes.add(quote);
		}
		
		QuoteResponse response = new QuoteResponse(quotes);
		
		//return list
		return response;
		
	}
	
	
	//Get list of all animals
	@GetMapping("/animals/all")
	AnimalResponse allAnimals() {
		
		//Create list of all
		List<QuoteAndAnimal> all = repo.findAll();
		//Start list of quotes
		List<Animal> animals = new ArrayList<>();
		
		//loop through them all, creating quotes and adding to list
		for (QuoteAndAnimal item: all) {
			Animal animal = new Animal(item.getId(), item.getAnimalUrl(), item.getType());
			animals.add(animal);
		}
		
		AnimalResponse response = new AnimalResponse(animals);
		
		//return list
		return response;
		
	}
	
	//Get list of quotes by tag
	@GetMapping("/quotes/tag/{tag}")
	QuoteResponse quotesByTag(@PathVariable String tag) {
		
		//Create list of all
		List<QuoteAndAnimal> all = repo.findByQuoteTag(tag);
		//Start list of quotes
		List<Quote> quotes = new ArrayList<>();
		
		//loop through them all, creating quotes and adding to list
		for (QuoteAndAnimal item: all) {
			Quote quote = new Quote(item.getId(), item.getQuote(), item.getAuthor(),
					item.getTag1(), item.getTag2());
			quotes.add(quote);
		}
		
		QuoteResponse response = new QuoteResponse(quotes);
		
		//return list
		return response;
		
	}
	
	//Get list of quotes by author
	@GetMapping("/quotes/author/{author}")
	QuoteResponse quotesByAuthor(@PathVariable String author) {
		
		//Create list of all
		List<QuoteAndAnimal> all = repo.findByQuoteAuthor(author);
		//Start list of quotes
		List<Quote> quotes = new ArrayList<>();
		
		//loop through them all, creating quotes and adding to list
		for (QuoteAndAnimal item: all) {
			Quote quote = new Quote(item.getId(), item.getQuote(), item.getAuthor(),
					item.getTag1(), item.getTag2());
			quotes.add(quote);
		}
		
		QuoteResponse response = new QuoteResponse(quotes);
		
		//return list
		return response;
		
	}
	
	//Get list of animals by type
	@GetMapping("/animals/type/{type}")
	AnimalResponse animalsByType(@PathVariable String type) {
		
		//Create list of all
		List<QuoteAndAnimal> all = repo.findByAnimalType(type);
		//Start list of quotes
		List<Animal> animals = new ArrayList<>();
		
		//loop through them all, creating quotes and adding to list
		for (QuoteAndAnimal item: all) {
			Animal animal = new Animal(item.getId(), item.getAnimalUrl(), item.getType());
			animals.add(animal);
		}
		
		AnimalResponse response = new AnimalResponse(animals);
		
		//return list
		return response;
		
	}
	
	
	//Get random quote
	@GetMapping("/quote/random")
	Quote randomQuote() {
		
		//Create list of all
		QuoteAndAnimal item = repo.findRandom();

		//Create an animal object from item
		Quote quote = new Quote(item.getId(), item.getQuote(), item.getAuthor(),
				item.getTag1(), item.getTag2());
		
		//return animal
		return quote;
		
	}
		
	//THIS ONE WORKS
	//Get random animal url
	@GetMapping("/animal/random")
	Animal randomAnimal() {
		
		//Create list of all
		QuoteAndAnimal item = repo.findRandom();

		//Create an animal object from item
		Animal animal = new Animal(item.getId(), item.getAnimalUrl(), item.getType());
		
		//return animal
		return animal;
		
	}
	
	
	//Get random quote by type
	@GetMapping("/quote/random/{tag}")
	Quote randomQuoteByTag(@PathVariable String tag) {
		
		//Create list of all by tag
		QuoteAndAnimal item = repo.findRandomByTag(tag);

		//Create a quote object from item
		Quote quote = new Quote(item.getId(), item.getQuote(), item.getAuthor(),
				item.getTag1(), item.getTag2());
		
		//return quote
		return quote;
		
	}

	
	//Get random animal by type
	@GetMapping("/animal/random/{type}")
	Animal randomAnimalByType(@PathVariable String type) {
		
		//Create list of all by type
		QuoteAndAnimal item = repo.findRandomByType(type);

		//Create an animal object from item
		Animal animal = new Animal(item.getId(), item.getAnimalUrl(), item.getType());
		
		//return animal
		return animal;
		
	}
	
	
	
	//Get list of authors
	@GetMapping("/quote/authors")
	List<String> allAuthors() {
		
		//Get list of all
		List<QuoteAndAnimal> all = repo.findAll();
		//Create list to store author
		List<String> authors = new ArrayList<>();
		
		//Loop through all objects, store object names
		//Could also do this, more simply, but oh well
		//I want to return it as a list anyway, not a set
		for (QuoteAndAnimal quote: all) {
			
			//if list of authors doesn't contain that author, add it
			if (!authors.contains(quote.getAuthor())) {
				authors.add(quote.getAuthor());
			}
			
		}
		
		return authors;
	}
	
		
	//Get list of quote tags
	@GetMapping("/quotes/tags")
	List<String> allQuoteTags() {
		
		//Get list of all
		List<QuoteAndAnimal> all = repo.findAll();
		//Create list to store author
		List<String> tags = new ArrayList<>();
		
		//Loop through all objects, store object names
		//Could also do this, more simply, but oh well
		//I want to return it as a list anyway, not a set
		for (QuoteAndAnimal quote: all) {
			
			//if list of tags doesn't contain tag1, add it
			if (!tags.contains(quote.getTag1())) {
				tags.add(quote.getTag1());
			}
			
			//if list of tags doesn't contain tag2, add it
			if (!tags.contains(quote.getTag2())) {
				tags.add(quote.getTag2());
			}
			
		}
		
		return tags;
	}
		
	//Get list of animal types
	@GetMapping("/animals/types")
	List<String> allAnimalTypes() {
		
		//Get list of all
		List<QuoteAndAnimal> all = repo.findAll();
		//Create list to store author
		List<String> types = new ArrayList<>();
		
		//Loop through all objects, store object names
		//Could also do this, more simply, but oh well
		//I want to return it as a list anyway, not a set
		for (QuoteAndAnimal animal: all) {
			
			//if list of authors doesn't contain that author, add it
			if (!types.contains(animal.getType())) {
				types.add(animal.getType());
			}
			
		}
		
		return types;
	}

}
