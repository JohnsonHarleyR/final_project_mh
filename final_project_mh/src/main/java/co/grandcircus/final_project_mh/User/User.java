package co.grandcircus.final_project_mh.User;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String username;
	private String email;
	private String password;
	private String name;
	private int points;
	private String friends;
	private String requests;
	private String status;
	
	public User() {
		
	}
	
	public User(String username, String email, String password, String name) {
		this.username = username.trim();
		this.email = email.trim();
		this.password = password;
		this.name = name.trim(); //I thought about the trim for when the name gets displayed
		this.points = 0;
		friends = "";
		requests = "";
		status = "user";
	}
	
	public Long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getFriends() {
		return friends;
	}

	public void setFriends(String friends) {
		this.friends = friends;
	}

	public String getRequests() {
		return requests;
	}

	public void setRequests(String requests) {
		this.requests = requests;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", name="
				+ name + ", points=" + points + ", friends=" + friends + ", requests=" + requests + ", status=" + status
				+ "]";
	}

	
	

}
