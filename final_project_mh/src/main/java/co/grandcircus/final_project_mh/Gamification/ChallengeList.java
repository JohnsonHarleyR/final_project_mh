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
@Table (name="challengelist")
public class ChallengeList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long challengeListId;
	private String name;
	private String description;
	private Long pointsReq;
	private Date datetime;
	private String category;
	private String prizeUrl;
	@ManyToOne
	private User user;
	
	public ChallengeList(String name, String description, Long pointsReq, Date datetime, String category,
			String prizeUrl, User user) {
		super();
		this.name = name;
		this.description = description;
		this.pointsReq = pointsReq;
		this.datetime = datetime;
		this.category = category;
		this.prizeUrl = prizeUrl;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public ChallengeList() {
		
	}

	public Long getChallengeListId() {
		return challengeListId;
	}

	public void setChallengeListId(Long challengeListId) {
		this.challengeListId = challengeListId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPointsReq() {
		return pointsReq;
	}

	public void setPointsReq(Long pointsReq) {
		this.pointsReq = pointsReq;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPrizeUrl() {
		return prizeUrl;
	}

	public void setPrizeUrl(String prizeUrl) {
		this.prizeUrl = prizeUrl;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

}
