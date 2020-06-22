package co.grandcircus.final_project_mh.Forum;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="thread")
public class thread {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="thread_id")
	private Long threadId;
	private String username;
	private Date datetime;
	private String messege;
	
	public thread() {
		
	}
	
	public thread(Long id, Long threadId, String username, Date datetime, String messege) {
		super();
		this.id = id;
		this.threadId = threadId;
		this.username = username;
		this.datetime = datetime;
		this.messege = messege;
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
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public String getMessege() {
		return messege;
	}
	public void setMessege(String messege) {
		this.messege = messege;
	}
	
	@Override
	public String toString() {
		return "thread [id=" + id + ", threadId=" + threadId + ", username=" + username + ", datetime=" + datetime
				+ ", messege=" + messege + "]";
	}
	
	
	
	
}
