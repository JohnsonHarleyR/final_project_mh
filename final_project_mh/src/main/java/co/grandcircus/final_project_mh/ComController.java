package co.grandcircus.final_project_mh;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.grandcircus.final_project_mh.User.UserDao;

@Controller
public class ComController {
	
	@Autowired
	private HttpSession session;
	@Autowired
	private UserDao userRepo;
	
}
