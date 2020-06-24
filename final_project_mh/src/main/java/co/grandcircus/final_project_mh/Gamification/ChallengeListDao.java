package co.grandcircus.final_project_mh.Gamification;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.final_project_mh.User.User;

public interface ChallengeListDao extends JpaRepository <ChallengeList, Long> {
	
	//List<ChallengeList> findChallengeListByUserId(Long id);
	List<ChallengeList> findAll();
	
	ChallengeList findByChallengeListId(Long id);

	ChallengeList findByCategory(String category);
	
}
