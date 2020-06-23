package co.grandcircus.final_project_mh.Forum;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="posts")
public class Posts {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	@Column(name="thread_id")
	private Long threadId;
	private LocalDateTime datetime;
	private String message;
	private Long discussionId;
	
	public Posts() {
		
	}
	
	
	public Posts( String username, Long threadId, LocalDateTime datetime, String message, Long discussionId) {
		super();
		this.username = username;
		this.threadId = threadId;
		this.datetime = datetime;
		this.message = message;
		this.discussionId = discussionId;
	}


	public Long getDiscussionId() {
		return discussionId;
	}


	public void setDiscussionId(Long discussionId) {
		this.discussionId = discussionId;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public Long getThreadId() {
		return threadId;
	}

	public void setThreadId(Long threadId) {
		this.threadId = threadId;
	}

	public LocalDateTime getDatetime() {
		return datetime;
	}
	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessege(String message) {
		this.message = message;
	}
	
	
}
	