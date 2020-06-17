package co.grandcircus.final_project_mh.Favorites;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AffirmationDao extends JpaRepository<FavAffirmation, Long> {
	
	List<FavAffirmation> findByUserId (long id);
	//List<FavAffirmation> findByDate (Date date);
}
