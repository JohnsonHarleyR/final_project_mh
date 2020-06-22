package co.grandcircus.final_project_mh.Forum;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="discussion")
public class Discussion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="discussion_id")
	private Long discussionId;
	@Column(name="post_type")
	private String postType;
	private String topic;
	private String username;
	
	
	
	
	public Discussion() {
		
	}
	
	public Discussion( Long threadId, String postType, String topic, String username
			) {
		super();
		
		this.discussionId = threadId;
		this.postType = postType;
		this.topic = topic;
		this.username = username;
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getThreadId() {
		return discussionId;
	}
	public void setThreadId(Long threadId) {
		this.discussionId = threadId;
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
	@Override
	public String toString() {
		return "Discussion [id=" + id + ", threadId=" + discussionId + ", postType=" + postType + ", topic=" + topic
				+ ", username=" + username + "]";
	}
	
	
	
	
}
