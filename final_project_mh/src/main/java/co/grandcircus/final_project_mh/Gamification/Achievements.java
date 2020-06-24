package co.grandcircus.final_project_mh.Gamification;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import co.grandcircus.final_project_mh.User.User;

@Entity
@Table (name="achievements")
public class Achievements {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long achievementsId;
	private String name;
	private Date date;
	private String description;
	
	@ManyToOne
	private User user;
	
	public Achievements() {
		
	}
	
	public Achievements(Long achievementsId, String name, Date date, String description) {
		super();
		this.achievementsId = achievementsId;
		this.name = name;
		this.date = date;
		this.description = description;
	}
	public Long getAchievementsId() {
		return achievementsId;
	}
	public void setAchievementsId(Long achievementsId) {
		this.achievementsId = achievementsId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
