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
@Table(name="thread")
public class Thread {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="thread_id")
	private Long threadId;
	private String username;
	private LocalDateTime datetime;
	private String messege;
	@Column(name="discussion_id")
	private Long discussionId;
	public Thread() {
	}
	
	
	
	
	public Thread( Long threadId, String username, LocalDateTime datetime, String messege,
			Long discussionId) {
		super();
		this.threadId = threadId;
		this.username = username;
		this.datetime = datetime;
		this.messege = messege;
		this.discussionId = discussionId;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public LocalDateTime getDatetime() {
		return datetime;
	}
	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}
	public String getMessege() {
		return messege;
	}
	public void setMessege(String messege) {
		this.messege = messege;
	}
	
	public Long getDiscussionId() {
		return discussionId;
	}

	public void setDiscussionId(Long discussionId) {
		this.discussionId = discussionId;
	}

	@Override
	public String toString() {
		return "thread [id=" + id + ", threadId=" + threadId + ", username=" + username + ", datetime=" + datetime
				+ ", messege=" + messege + "]";
	}
	
	
	
	
}
