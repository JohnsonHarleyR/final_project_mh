package co.grandcircus.final_project_mh.Gamification;

import javax.persistence.Column;
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
	private Integer points;
	@ManyToOne
	private User user;
	private String date;
	@Column(name="on_profile")
	private int onProfile;
	
	public Challenge() {		
	}
		
	public Long getChallengeListId() {
		return challengelistid;
	}
	public void setChallengeListId(Long challengeListId) {
		this.challengelistid = challengeListId;
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
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
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

	@Override
	public String toString() {
		return "Challenge [challengeid=" + challengeid + ", complete=" + complete + ", challengelistid="
				+ challengelistid + ", points=" + points + ", user=" + user + ", date=" + date + ", onProfile="
				+ onProfile + "]";
	}
	
	

	
}
