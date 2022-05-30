package dev.com.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Posting {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "POST_ID")
	private long postId;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class) // student와 major의 관계는 다대일 (N:1)
	@JoinColumn(name = "user_index")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Category.class)
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;
	
	@Column(name ="TITLE")
	private String title;
	
	@Column(name = "DESCRIPTION")
	private String description;

	public Posting() {
	}
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Posting [postId=" + postId + ", user=" + user.getName() + ", category=" + category.getCategoryName() + ", title=" + title
				+ ", description=" + description + "]";
	}
	
	




	
	
}