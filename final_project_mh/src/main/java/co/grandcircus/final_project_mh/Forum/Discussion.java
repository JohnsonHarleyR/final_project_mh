package co.grandcircus.final_project_mh.Forum;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="discussions")
public class Discussion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="post_type")
	private String postType;
	private String description;
	private String topic;
	private String tag;
	
	//to determine who posted the discussion to start 
	private String username;
	
	
	
	
	public Discussion() {
		
	}
	
	
	public Discussion(Long id, String postType, String description, String topic, String tag, String username) {
		super();
		this.id = id;
		this.postType = postType;
		this.description = description;
		this.topic = topic;
		this.tag = tag;
		this.username = username;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getPostType() {
		return postType;
	}
	public void setPostType(String postType) {
		this.postType = postType;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "Discussion [id=" + id + ", postType=" + postType + ", topic=" + topic + ", username=" + username + "]";
	}
	
	
	
	
}
