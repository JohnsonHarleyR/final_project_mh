package co.grandcircus.final_project_mh.Favorites;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FavArticle implements Comparable<FavArticle>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date datetime;
	private String title;
	private String description;
	private String url;
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "on_profile")
	private int onProfile;
	
	public FavArticle() {}

public FavArticle(Date datetime, String title, String description, String url, Long userId) {
		super();
		this.datetime = datetime;
		this.title = title;
		this.description = description;
		this.url = url;
		this.userId = userId;
		
		onProfile = 0;
	}

public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	

public int getOnProfile() {
		return onProfile;
	}

	public void setOnProfile(int onProfile) {
		this.onProfile = onProfile;
	}
	
	

@Override
	public String toString() {
		return "FavArticle [id=" + id + ", datetime=" + datetime + ", title=" + title + ", description=" + description
				+ ", url=" + url + ", userId=" + userId + ", onProfile=" + onProfile + "]";
	}

@Override
public int compareTo(FavArticle o) {
	if (datetime == o.getDatetime()) {
		return id.compareTo(o.getId());
	} else {
		return datetime.compareTo(o.getDatetime());
	}	
}
}