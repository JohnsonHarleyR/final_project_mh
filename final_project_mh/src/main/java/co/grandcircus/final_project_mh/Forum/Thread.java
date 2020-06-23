package co.grandcircus.final_project_mh.Forum;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="threads")
public class Thread {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="thread_title")
	private String threadTitle;
	@Column(name="discussion_id")
	private Long discussionId;
	private String username;
	private LocalDateTime datetime;
	
	public Thread() {
		
	}

	public Thread( String threadTitle, Long discussionId, String username, LocalDateTime datetime) {
		super();
		this.threadTitle = threadTitle;
		this.discussionId = discussionId;
		this.username = username;
		this.datetime = datetime;
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



	public String getThreadTitle() {
		return threadTitle;
	}
	public void setThreadTitle(String threadTitle) {
		this.threadTitle = threadTitle;
	}
	public Long getDiscussionId() {
		return discussionId;
	}
	public void setDiscussionId(Long discussionId) {
		this.discussionId = discussionId;
	}



	public LocalDateTime getDatetime() {
		return datetime;
	}
	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}



	@Override
	public String toString() {
		return "Thread [id=" + id + ", threadTitle=" + threadTitle + ", discussionId=" + discussionId + ", username="
				+ username + ", datetime=" + datetime + "]";
	}
	
	
}
