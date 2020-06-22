package HarleyJohnson.HealingApi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import HarleyJohnson.HealingApi.entity.QuoteAndAnimal;

public interface QuoteAndAnimalDao extends JpaRepository<QuoteAndAnimal, Long> {
	
	//Get all
	@Query (nativeQuery=true, value="SELECT * FROM quotes_and_animals")
	List<QuoteAndAnimal> findAll();
	
	//Get list of quotes by Author
	List<QuoteAndAnimal> findByAuthor(String author);
	
	//Get list by animal type
	List<QuoteAndAnimal> findByType(String type);
	
	//Get list by quote tag
	@Query (nativeQuery=true, value="SELECT * FROM quotes_and_animals WHERE tag1 LIKE :tag "
			+ "OR tag2 LIKE :tag")
	List<QuoteAndAnimal> findByQuoteTag(@Param("tag") String tag);
	
	//Get list by quote author
	@Query (nativeQuery=true, value="SELECT * FROM quotes_and_animals WHERE author LIKE :author")
	List<QuoteAndAnimal> findByQuoteAuthor(@Param("author") String author);
	
	//Get list by animal type
	@Query (nativeQuery=true, value="SELECT * FROM quotes_and_animals WHERE type LIKE :type")
	List<QuoteAndAnimal> findByAnimalType(@Param("type") String type);
	
	//Get random QuoteAndAnimal
	@Query (nativeQuery=true, value="SELECT * FROM quotes_and_animals ORDER BY RAND() LIMIT 1")
	QuoteAndAnimal findRandom();
	
	//Get random QuoteAndAnimal by matching quote tag
	@Query (nativeQuery=true, value="SELECT * FROM quotes_and_animals WHERE tag1 LIKE :tag "
			+ "OR tag2 LIKE :tag ORDER BY RAND() LIMIT 1")
	QuoteAndAnimal findRandomByTag(@Param("tag") String tag);
	
	//Get random QuoteAndAnimal by matching animal type
	@Query (nativeQuery=true, value="SELECT * FROM quotes_and_animals WHERE type LIKE :type "
			+ "ORDER BY RAND() LIMIT 1")
	QuoteAndAnimal findRandomByType(@Param("type") String type);
	

}
