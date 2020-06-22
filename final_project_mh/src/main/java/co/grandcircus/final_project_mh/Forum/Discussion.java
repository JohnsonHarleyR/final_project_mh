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
	@Column(name="thread_id")
	private Long threadId;
	@Column(name="post_type")
	private String postType;
	private String topic;
	private Integer replies;
	private String username;
	private Date datetime;
	
	
	
	
	public Discussion() {
		
	}
	
	public Discussion(Long id, Long threadId, String postType, String topic, Integer replies, String username,
			Date datetime) {
		super();
		this.id = id;
		this.threadId = threadId;
		this.postType = postType;
		this.topic = topic;
		this.replies = replies;
		this.username = username;
		this.datetime = datetime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getThreadId() {
		return threadId;
	}
	public void setThreadId(Long threadId) {
		this.threadId = threadId;
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
	public Integer getReplies() {
		return replies;
	}
	public void setReplies(Integer replies) {
		this.replies = replies;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	@Override
	public String toString() {
		return "Discussion [id=" + id + ", threadId=" + threadId + ", postType=" + postType + ", topic=" + topic
				+ ", replies=" + replies + ", username=" + username + ", datetime=" + datetime + "]";
	}
	
	
	
	
}
