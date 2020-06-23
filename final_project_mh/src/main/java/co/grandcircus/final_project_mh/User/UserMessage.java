package co.grandcircus.final_project_mh.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_messages")
public class UserMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "conversation_ref")
	private String conversationRef;
	@Column(name = "sender_id")
	private Long senderId;
	@Column(name = "receiver_id")
	private Long receiverId;
	private LocalDateTime datetime;
	private String message;
	@Column(name = "is_read")
	private int isRead;
	
	private String cleanDatetime;
	
	
	public UserMessage() {
		this.isRead = 0;
	}
	
	public UserMessage(String conversationRef, Long senderId, Long receiverId, LocalDateTime datetime,
			String message) {
		super();
		this.conversationRef = conversationRef;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.datetime = datetime;
		this.message = message;
		
		isRead = 0;
		
		
		//Store cleanDatetime as string from datetime
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("E dd MMM yyyy");
        cleanDatetime = datetime.format(formatter1) + " at " +
        		datetime.format(formatter2);
		
	}
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConversationRef() {
		return conversationRef;
	}
	public void setConversationRef(String conversationRef) {
		this.conversationRef = conversationRef;
	}
	public Long getSenderId() {
		return senderId;
	}
	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}
	public Long getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
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
	public void setMessage(String message) {
		this.message = message;
	}

	public int getIsRead() {
		return isRead;
	}

	public void setIsRead(int read) {
		this.isRead = read;
	}

	public String getCleanDatetime() {
		//Store cleanDatetime as string from datetime
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("E dd MMM yyyy");
        cleanDatetime = datetime.format(formatter1) + " at " +
        		datetime.format(formatter2);
		return cleanDatetime;
	}

	public void setCleanDatetime(String cleanDatetime) {
		this.cleanDatetime = cleanDatetime;
	}

	@Override
	public String toString() {
		return "UserMessages [id=" + id + ", conversationRef=" + conversationRef + ", senderId=" + senderId
				+ ", receiverId=" + receiverId + ", datetime=" + datetime + ", message=" + message + "isRead=" +
				isRead + "]";
	}
	
	
}
