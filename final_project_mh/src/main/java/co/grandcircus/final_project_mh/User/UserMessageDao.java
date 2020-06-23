package co.grandcircus.final_project_mh.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMessageDao extends JpaRepository<UserMessage, Long> {

	List<UserMessage> findByConversationRef(String conversationRef);
	
	Optional<UserMessage> findById(Long id);
	
}
