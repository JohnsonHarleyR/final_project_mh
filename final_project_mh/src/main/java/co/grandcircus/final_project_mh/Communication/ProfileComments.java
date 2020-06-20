package co.grandcircus.final_project_mh.Communication;

import java.sql.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.servlet.http.HttpSession;

import co.grandcircus.final_project_mh.User.User;
import co.grandcircus.final_project_mh.User.UserDao;


@Entity
@Table(name="profile_comments")
public class ProfileComments implements Comparable<ProfileComments> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String comment;
	@Column(name="commenter_id")
	private Long commenterId;
	private Date datetime;
	@Column(name="profile_id")
	private Long profileId;
	@Column(name="commenter_username")
	private String username;
	
	
	public ProfileComments() {
		
	}
	
	public ProfileComments(String comment, Long commenterId, Date datetime, Long profileId, UserDao repo) {
		
		super();
		this.comment = comment;
		this.commenterId = commenterId;
		this.datetime = datetime;
		this.profileId = profileId;
		
		//Get username from session and commenterId
		//It's just easier to do it this way after everything
		Optional<User> opt = repo.findById(commenterId);
		User commenter = opt.get();
		
		username = commenter.getUsername();
		
		
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Long getCommenterId() {
		return commenterId;
	}
	public void setCommenterId(Long commenterId) {
		this.commenterId = commenterId;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	
	public Long getProfileId() {
		return profileId;
	}
	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	@Override
	public String toString() {
		return "ProfileComments [id=" + id + ", comment=" + comment + ", commenterId=" + commenterId + ", datetime="
				+ datetime + ", profileId=" + profileId + ", username=" + username + "]";
	}

	@Override
	public int compareTo(ProfileComments o) {
		if (datetime == o.getDatetime()) {
			return id.compareTo(o.getId());
		} else {
			return datetime.compareTo(o.getDatetime());
		}
		
	}
	
	
}
