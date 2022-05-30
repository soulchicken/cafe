package dev.com.controller;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import dev.com.model.Category;
import dev.com.model.Posting;
import dev.com.model.User;
@RestController
public class PostingController {
	
	@GetMapping(value = "/uploadPosting")
	public void uploadPosting(@RequestParam("id") Long index, @RequestParam("post") String post,
			@RequestParam("category") Long categoryId,
			@RequestParam("title") String title) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cafe_test3");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			User user = em.find(User.class, index);
			Posting newPost = new Posting();
			Category category2 = em.find(Category.class,categoryId);

			newPost.setTitle(title);
			newPost.setDescription(post);
			newPost.setCategory(category2);
			
			newPost.setUser(user);
			
			List<Posting> userPosting = user.getPosting();
			userPosting.add(newPost);

			List<Posting> categoryPosting = category2.getPosting();
			categoryPosting.add(newPost);
			
			
			em.persist(user);
			em.persist(category2);
			em.persist(newPost);
			
		} catch (Exception e) {
			System.out.println("포스팅 실패");
		} finally {
			tx.commit();
			em.close();
			emf.close();
		}
	}
	
	@PatchMapping(value = "/updatePosting")
	public void updatePosting( @RequestParam("description") String description,
			@RequestParam("category") Long category,
			@RequestParam("title") String title, @RequestParam("postId") Long postId) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cafe_test3");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Posting posting = em.find(Posting.class, postId);
			posting.setTitle(title);
			posting.setDescription(description);
			Category category2 = em.find(Category.class, category);
			posting.setCategory(category2);
			
			List<Posting> postings =  category2.getPosting();
			postings.add(posting);
			em.persist(posting);
			em.persist(category2);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			tx.commit();
			em.close();
			emf.close();			
		}
	}
	
	
	@RequestMapping("/readPosting")
	public String postRead(@RequestParam("postId") Long postId) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cafe_test3");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		String result = "";
		try {
			tx.begin();
			Posting post = em.find(Posting.class, postId);
			result = post.toString();
			
		} catch (Exception e) {
			System.out.println("해당 게시글이 존재하지 않습니다.");
		} finally {
			tx.commit();
			em.close();
			emf.close();
		}
	
		return result;
	}
	
	@DeleteMapping("/deletePosting")
	public String deleteCategory(@RequestParam("post_id") Long postId) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cafe_test3");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		String result = "";
		try {
			tx.begin();
			Posting posting = em.find(Posting.class, postId);
			em.remove(posting);
			result = posting.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			tx.commit();
			em.close();
			emf.close();
			
		}
		return result;
	}
}