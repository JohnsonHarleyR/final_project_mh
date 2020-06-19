package co.grandcircus.final_project_mh.Communication;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


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
	public ProfileComments() {
		
	}
	
	public ProfileComments(String comment, Long commenterId, Date datetime, Long profileId) {
		super();
		this.comment = comment;
		this.commenterId = commenterId;
		this.datetime = datetime;
		this.profileId = profileId;
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
	@Override
	public String toString() {
		return "ProfileComments [id=" + id + ", comment=" + comment + ", commenterId=" + commenterId + ", datetime="
				+ datetime + ", profileId=" + profileId + "]";
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
