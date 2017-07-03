package pl.destino.springchat.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CHAT_USER")
public class ChatUser implements Serializable{
	@Id
	@Column(name="USER_ID", nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Column(name="USER_NAME", nullable=false)
	private String userName;
	
	@Column(name="PASSWORD")
	private String userPassword;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
	   name = "CHAT_USER_ROLE", 
	   joinColumns = @JoinColumn(name = "USER_ID"), 
	   inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
	)
	private List<ChatRole> roles;
	
	public ChatUser()	{
		// no args constructor needed by JPA
	}
	
	public ChatUser(String userName, String userPassword)	{
		this.userName = userName;
		this.userPassword = userPassword;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public List<ChatRole> getRoles() {
		return roles;
	}

	public void setRoles(List<ChatRole> roles) {
		this.roles = roles;
	}
	
	
}
