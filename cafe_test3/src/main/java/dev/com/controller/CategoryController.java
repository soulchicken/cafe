package dev.com.controller;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import dev.com.model.Category;
@RestController
public class CategoryController{
	@GetMapping("/createCategory")
	public Category getCategory(@RequestParam("name") String name) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cafe_test3");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		Category category = new Category();
		category.setCategoryName(name);
		em.persist(category);
		System.out.println(category);
		
		tx.commit();
		return category;
	}
	
//	// Read (get)
	@GetMapping("/readCategory")
	public Category ex5(@RequestParam("id") long id) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cafe_test3");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		Category category = em.find(Category.class, id);
		System.out.println(category);
		tx.commit();
		return category;
	}
	
	@PatchMapping("/updateCategory")
	public Category updateCategory(@RequestParam("index") Long index ,@RequestParam("newname") String newName ) {
//		String findQuery = "select m from Category as m where m."
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cafe_test3");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		Category category = em.find(Category.class, index);
		category.setCategoryName(newName);
		em.persist(category);
		System.out.println(category);
		
		tx.commit();
		return category;
	}
	
	@DeleteMapping("/delCategory")
	public Category deleteCategory(@RequestParam("c_index") Long id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cafe_test3");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Category category = em.find(Category.class, id);
		em.remove(category);
		tx.commit();
		return category;
	}
}