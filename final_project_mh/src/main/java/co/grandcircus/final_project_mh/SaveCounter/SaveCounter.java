package co.grandcircus.final_project_mh.SaveCounter;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//This counts the number of saves per day per user
//for the sake of determining whether to give points

@Entity
@Table(name = "save_counter")
public class SaveCounter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private Date datetime;
	@Column(name = "complete_exercises")
	private int completeExercises;
	private int articles;
	private int affirmations;
	private int records;
	@Column(name = "user_id")
	private long userId;
	
	
	
	public SaveCounter() {}
	
	public SaveCounter(Date datetime, int completeExercises, int articles, int affirmations, int records, long userId) {
		super();
		this.datetime = datetime;
		this.completeExercises = completeExercises;
		this.articles = articles;
		this.affirmations = affirmations;
		this.records = records;
		this.userId = userId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public int getCompleteExercises() {
		return completeExercises;
	}

	public void setCompleteExercises(int completeExercises) {
		this.completeExercises = completeExercises;
	}

	public int getArticles() {
		return articles;
	}

	public void setArticles(int articles) {
		this.articles = articles;
	}

	public int getAffirmations() {
		return affirmations;
	}

	public void setAffirmations(int affirmations) {
		this.affirmations = affirmations;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "SaveCounter [id=" + id + ", datetime=" + datetime + ", completeExercises=" + completeExercises
				+ ", articles=" + articles + ", affirmations=" + affirmations + ", records=" + records + ", userId="
				+ userId + "]";
	}
	
	
}
