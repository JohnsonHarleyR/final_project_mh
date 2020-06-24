package co.grandcircus.final_project_mh.Gamification;

import javax.persistence.Column;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import co.grandcircus.final_project_mh.User.User;

@Entity
@Table (name = "challenge") 
public class Challenge {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long challengeid;
	private boolean complete;
	private Long challengelistid;
	private Long points;
	private String category;
	private String prizeUrl;
	private String name;
	private String description;
	@ManyToOne
	private User user;
	private Date date;
	@Column(name="on_profile")
	private int onProfile;
	
	
	public Challenge() {		
	}
		
	public Long getChallengeListId() {
		return challengelistid;
	}
	public void setChallengeListId(ChallengeList challengeListId) {
		this.challengelistid = challengeListId.getChallengeListId();
	}
	public boolean isComplete() {
		return complete;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	public Long getChallengeId() {
		return challengeid;
	}
	public void setChallengeId(Long challengeId) {
		this.challengeid = challengeId;
	}
	public Long getPoints() {
		return points;
	}
	public void setPoints(Long points) {
		this.points = points;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public Long getChallengeid() {
		return challengeid;
	}

	public void setChallengeid(Long challengeid) {
		this.challengeid = challengeid;
	}

	public Long getChallengelistid() {
		return challengelistid;
	}

	public void setChallengelistid(Long challengelistid) {
		this.challengelistid = challengelistid;
	}

	public int getOnProfile() {
		return onProfile;
	}

	public void setOnProfile(int onProfile) {
		this.onProfile = onProfile;
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
	
	@Override
	public String toString() {
		return "Challenge [challengeid=" + challengeid + ", complete=" + complete + ", challengelistid="
				+ challengelistid + ", points=" + points + ", user=" + user + ", date=" + date + ", onProfile="
				+ onProfile + "]";
	}
	
}
