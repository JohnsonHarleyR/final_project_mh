package co.grandcircus.final_project_mh.Communication;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileCommentsDao extends JpaRepository<ProfileComments,Long> {

	List<ProfileComments> findByCommenterId(Long commenterId);
	List<ProfileComments> findByProfileId(Long profileId);
}
