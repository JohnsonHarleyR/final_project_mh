package co.grandcircus.final_project_mh.User;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
	User findByEmail(String email);
	User findByid(Long id);
	

}
