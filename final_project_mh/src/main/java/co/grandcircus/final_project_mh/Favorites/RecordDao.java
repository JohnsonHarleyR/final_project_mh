package co.grandcircus.final_project_mh.Favorites;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordDao extends JpaRepository<Record, Long> {

	List<Record> findByUserId (long id);
	//List<Record> findByDate (Date date);
	
}
