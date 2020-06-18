package co.grandcircus.final_project_mh.UserPreferences;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_preferences")
public class UserPreferences implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer userWeight;
	private String mentalIllnesses;
	private String musicGenrePreferences;
	private Integer userGoalWeight;
	
	@Column(name = "user_id")
	private Long userId;

	public UserPreferences() {
		
	}
	
	public UserPreferences(Long id, Integer userWeight, String mentalIllnesses, String musicGenrePreferences,
			String bodyGoalText, Integer userGoalWeight, Long userId) {
		super();
		this.id = id;
		this.userWeight = userWeight;
		this.mentalIllnesses = mentalIllnesses;
		this.musicGenrePreferences = musicGenrePreferences;
		this.userGoalWeight = userGoalWeight;
		this.userId = userId;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUserWeight() {
		return userWeight;
	}

	public void setUserWeight(Integer userWeight) {
		this.userWeight = userWeight;
	}

	public String getMentalIllnesses() {
		return mentalIllnesses;
	}

	public void setMentalIllnesses(String mentalIllnesses) {
		this.mentalIllnesses = mentalIllnesses;
	}

	public String getMusicGenrePreferences() {
		return musicGenrePreferences;
	}

	public void setMusicGenrePreferences(String musicGenrePreferences) {
		this.musicGenrePreferences = musicGenrePreferences;
	}


	public Integer getUserGoalWeight() {
		return userGoalWeight;
	}

	public void setUserGoalWeight(Integer userGoalWeight) {
		this.userGoalWeight = userGoalWeight;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserPreferences [id=" + id + ", userWeight=" + userWeight + ", mentalIllnesses=" + mentalIllnesses
				+ ", musicGenrePreferences=" + musicGenrePreferences 
				+ ", userGoalWeight=" + userGoalWeight + ", userId=" + userId + "]";
	}
	
	
}
