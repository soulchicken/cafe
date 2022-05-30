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
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CATEGORY_ID")
	private Long categoryId;
	
	@Column(name = "CATEGORY_NAME")
	private String categoryName;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
	private List<Posting> posting = new ArrayList<>();
	
	
	public List<Posting> getPosting() {
		return posting;
	}
	public void setPosting(List<Posting> posting) {
		this.posting = posting;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		String result = "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", posting size=" + posting.size() + "]";
		for (Posting post : posting) {
			result += post.getTitle() + "\n";
		}
		return result;
	}
	
	
}