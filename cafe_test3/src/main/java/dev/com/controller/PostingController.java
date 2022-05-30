package dev.com.controller;

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
			@RequestParam("category") Long category_id,
			@RequestParam("title") String title) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cafe_test3");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			User user = em.find(User.class, index);

			Posting newPost = new Posting();
			newPost.setTitle(title);
			newPost.setDescription(post);

			Category category2 = em.find(Category.class,category_id);

			newPost.setCategory(category2);
			newPost.setUser(user);
			
			em.persist(newPost);
			em.persist(category2);

			user.getPosting().add(newPost);
			em.persist(user);

			tx.commit();
		} catch (Exception e) {
			System.out.println("포스팅 실패");
		} finally {
			em.close();
			emf.close();
		}
	}
	
	@PatchMapping(value = "/updatePosting")
	public void updatePosting(@RequestParam("id") Long index, @RequestParam("description") String description,
			@RequestParam("category") Long category,
			@RequestParam("title") String title) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cafe_test3");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			User user = em.find(User.class, index);

			Posting newPost = new Posting();
			newPost.setTitle(title);
			newPost.setDescription(description);

			Category category2 = new Category();
			category2.setCategoryId(category);

			newPost.setCategory(category2);
			em.persist(newPost);
			em.persist(category2);

			user.getPosting().add(newPost);
			em.persist(user);

			tx.commit();
		} catch (Exception e) {
			em.close();
			emf.close();
		}
	}
	
	
	@RequestMapping("/readPosting")
	public Posting postRead(@RequestParam("postId") Long postId) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cafe_test3");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			Posting post = em.find(Posting.class, postId);
			
			// 있어야할 것
			// postId, 제목, 작성자, 게시판(카테고리)
			if(post.getPostId() != 0L && post.getTitle() != null && post.getUser() != null && post.getCategory() != null) {
				return post;
			}
		} catch (Exception e) {
			System.out.println("해당 게시글이 존재하지 않습니다.");
		}
	
		return null;
	}

	@DeleteMapping("/deletePosting")
	public void ex4(@RequestParam("id") long index) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cafe_test3");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			User user = em.find(User.class, index);
			System.out.println(user);
			em.remove(user);

			tx.commit();
		} catch (Exception e) {
			System.out.println("삭제실패");
		}
	}
}