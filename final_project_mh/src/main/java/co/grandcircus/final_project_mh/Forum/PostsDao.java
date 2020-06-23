package co.grandcircus.final_project_mh.Forum;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsDao extends JpaRepository<Posts, Long>{
	
	List<Posts> findByThreadId(Long id);

}
