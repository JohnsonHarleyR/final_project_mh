package co.grandcircus.final_project_mh.Gamification;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.final_project_mh.User.User;

public interface ChallengeDao extends JpaRepository <Challenge, Long>{
	
	Challenge findByComplete(boolean complete);
	
	List<Challenge> findChallengeByUserId(Long id);
	
	public void deleteById(Long id);
	
}
