package co.grandcircus.final_project_mh.Favorites;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleDao extends JpaRepository<FavArticle, Long> {
	List<FavArticle> findByUserId (long id);
	//List<FavAffirmation> findByDate (Date date);	
}
