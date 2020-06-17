package co.grandcircus.final_project_mh.Gamification;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.final_project_mh.User.User;

public interface ChallengeListDao extends JpaRepository <ChallengeList, Long> {
	
	ChallengeList findByUserId(Long userId);

	ChallengeList findByCategory(String category);
	
}
