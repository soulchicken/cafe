package dev.com.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_index")
	private long index;

	@Column(name = "user_id", nullable = false)
	private String id;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "user_name", nullable = false)
	private String name;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private List<Posting> posting = new ArrayList<>();

	public User() {
	}

	public User(Long index, String password) {
		this.index = index;
		this.password = password;
	}

	
	
	public User(String password, String name) {
		super();
		this.password = password;
		this.name = name;
	}

	public User(String id, String password, String name) {
		this.id = id;
		this.password = password;
		this.name = name;
	}
	

	public Long getIndex() {
		return index;
	}

	public void setIndex(Long index) {
		this.index = index;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public List<Posting> getPosting() {
		return posting;
	}

	public void setPosting(List<Posting> posting) {
		this.posting = posting;
	}

	@Override
	public String toString() {
		String result = "User [index=" + index + ", id=" + id + ", password=" + password + ", name=" + name + ", posting length ="
				+ posting.size() + "]\n";
		
		for (Posting post : posting) {
			result += post.getTitle() + "\n";
		}
		System.out.println(result);
		return result;
	}
	

}