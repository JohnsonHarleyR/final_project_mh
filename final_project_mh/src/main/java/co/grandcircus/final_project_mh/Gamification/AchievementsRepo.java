package co.grandcircus.final_project_mh.Gamification;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementsRepo extends JpaRepository<Achievements, Long>{

	List<Achievements> findAchievementsByUserId(Long id);
	
	
	
	
	
}
