package co.grandcircus.final_project_mh.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import co.grandcircus.final_project_mh.Favorites.FavAffirmation;

//to help display message list

public class Conversation implements Comparable<Conversation> {
	
	private Long id;
	private User friend;
	private String newestMessage;
	private String abridgedMsg;
	private LocalDateTime newestDatetime;
	private String cleanDatetime;
	private boolean isRead;
	
	
	
	public Conversation(Long id, User friend, String newestMessage, LocalDateTime newestDatetime, boolean isRead) {
		super();
		this.id = id;
		this.friend = friend;
		this.newestMessage = newestMessage;
		this.newestDatetime = newestDatetime;
		this.isRead = isRead;
		
		//Store cleanDatetime as string from datetime
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("E dd MMM yyyy");
        cleanDatetime = newestDatetime.format(formatter1) + " at " +
        		newestDatetime.format(formatter2);
        
        //Create abridged message
        if (newestMessage.length() > 29) {
        	abridgedMsg = newestMessage.substring(0, 29);
        } else {
        	abridgedMsg = newestMessage.substring(0, newestMessage.length() - 1);
        }
        
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public boolean getIsRead() {
		return isRead;
	}
	public void setIsRead(boolean isRead) {
		this.isRead = isRead;
	}
	
	
	
	public String getAbridgedMsg() {
		//Create abridged message
        if (newestMessage.length() > 29) {
        	abridgedMsg = newestMessage.substring(0, 29);
        } else {
        	abridgedMsg = newestMessage.substring(0, newestMessage.length() - 1);
        }
		
		return abridgedMsg;
	}

	public void setAbridgedMsg(String abridgedMsg) {
		this.abridgedMsg = abridgedMsg;
	}

	public String getCleanDatetime() {
		
		//Store cleanDatetime as string from datetime
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("E dd MMM yyyy");
        cleanDatetime = newestDatetime.format(formatter1) + " at " +
        		newestDatetime.format(formatter2);
		
		return cleanDatetime;
	}

	public void setCleanDatetime(String cleanDatetime) {
		this.cleanDatetime = cleanDatetime;
		
	}

	@Override
	public String toString() {
		return "Conversation [friend=" + friend + ", newestMessage=" + newestMessage + ", newestDatetime="
				+ newestDatetime + ", read=" + isRead + "]";
	}
	

	
	@Override
	public int compareTo(Conversation o) {

			return newestDatetime.compareTo(o.getNewestDatetime());
		
	}
	
	

}
