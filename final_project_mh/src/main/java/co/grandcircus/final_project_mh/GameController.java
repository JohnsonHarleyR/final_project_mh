package co.grandcircus.final_project_mh;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.grandcircus.final_project_mh.Gamification.ChallengeDao;
import co.grandcircus.final_project_mh.Gamification.ChallengeList;
import co.grandcircus.final_project_mh.Gamification.ChallengeListDao;
import co.grandcircus.final_project_mh.User.User;

//test controller: made as a template to be implemented across app

@Controller
public class GameController {
	
	@Autowired ChallengeDao ChallengeRepo;
	@Autowired ChallengeListDao ChallengeListRepo;
	@Autowired private HttpSession session;

	@RequestMapping("/challenge-form")
	public String challengeForm(Model model) {
		
		
		boolean loggedIn = Methods.checkLogin(session);
		User user = (User)session.getAttribute("user");
		
		model.addAttribute("loggedin", loggedIn);
		
		List<co.grandcircus.final_project_mh.Gamification.Challenge> challenge;
		
	    //adds total points of users accomplishments
		if(user != null) {
		
		challenge = ChallengeRepo.findChallengeByUserId(user.getId());
		int points = 0;
		for(int i =0; i < challenge.size(); i++)
		{points = points + challenge.get(i).getPoints();
		System.out.print(points);
		}
		}
		
	return "challenge-form";
	}
	
	@RequestMapping("/submit-challenge")
	
	public String submitChallenge(@RequestParam (value="category") String category, 
			@RequestParam(value = "description")String description,
			@RequestParam(value="name")String name, @RequestParam(value = "points_req") Long points_req, 
			@RequestParam(value = "prize_url")String prize_url, Model model){
		
		    User user;
		    user=(User)session.getAttribute("user");
		   // Long user_id = user.getId(); 
		
		    ChallengeList challengeList = new ChallengeList();
		    
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Date date=new Date(timestamp.getTime());
		    
		    challengeList.setCategory(category);
		    challengeList.setDatetime(date);
		    challengeList.setDescription(description);
		    challengeList.setName(name);
		    challengeList.setPointsReq(points_req);
		    challengeList.setPrizeUrl(prize_url);
		    challengeList.setUser(user);
		    
		    ChallengeListRepo.save(challengeList);
		    
		
		return "redirect:/challenge-form";
	}

}
