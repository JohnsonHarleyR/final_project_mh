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
	@Column(name="user_id")
	private Long userId;
	@Column(name="number_of_posts")
	private Long numberOfPosts;
	//Every time a thread is created, we must go back and set this variable to the id of the first
	//post.
	//And if the first post is deleted, the thread must be deleted.
	@Column(name="last_post_id")
	private Long lastPostId;
	
	public Thread() {
		
	}
	//a couple of these are left out of the constructor on purpose
	public Thread( String threadTitle, Long discussionId, String username,
			LocalDateTime datetime, Long userId) {
		super();
		this.threadTitle = threadTitle;
		this.discussionId = discussionId;
		this.username = username;
		this.datetime = datetime;
		this.userId = userId;
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

	
	


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	

	public Long getNumberOfPosts() {
		return numberOfPosts;
	}
	public void setNumberOfPosts(Long numberOfPosts) {
		this.numberOfPosts = numberOfPosts;
	}
	public Long getLastPostId() {
		return lastPostId;
	}

	public void setLastPostId(Long lastPostId) {
		this.lastPostId = lastPostId;
	}

	@Override
	public String toString() {
		return "Thread [id=" + id + ", threadTitle=" + threadTitle + ", discussionId=" + discussionId + ", username="
				+ username + ", datetime=" + datetime + ", userId=" + userId + ", lastPostId=" + lastPostId + "]";
	}

	
	
	
	
}
