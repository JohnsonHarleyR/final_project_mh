package co.grandcircus.final_project_mh.User;

import java.time.LocalDateTime;

import co.grandcircus.final_project_mh.Favorites.FavAffirmation;

//to help display message list

public class Conversation implements Comparable<Conversation> {
	
	private int id;
	private User friend;
	private String newestMessage;
	private LocalDateTime newestDatetime;
	private boolean read;
	
	
	
	public Conversation(int id, User friend, String newestMessage, LocalDateTime newestDatetime, boolean read) {
		super();
		this.id = id;
		this.friend = friend;
		this.newestMessage = newestMessage;
		this.newestDatetime = newestDatetime;
		this.read = read;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getFriend() {
		return friend;
	}
	public void setFriend(User friend) {
		this.friend = friend;
	}
	public String getNewestMessage() {
		return newestMessage;
	}
	public void setNewestMessage(String newestMessage) {
		this.newestMessage = newestMessage;
	}
	public LocalDateTime getNewestDatetime() {
		return newestDatetime;
	}
	public void setNewestDatetime(LocalDateTime newestDatetime) {
		this.newestDatetime = newestDatetime;
	}
	public boolean isRead() {
		return read;
	}
	public void setRead(boolean read) {
		this.read = read;
	}
	@Override
	public String toString() {
		return "Conversation [friend=" + friend + ", newestMessage=" + newestMessage + ", newestDatetime="
				+ newestDatetime + ", read=" + read + "]";
	}
	

	
	@Override
	public int compareTo(Conversation o) {

			return newestDatetime.compareTo(o.getNewestDatetime());
		
	}
	
	

}
