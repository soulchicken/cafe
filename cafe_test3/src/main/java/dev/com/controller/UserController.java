package dev.com.controller;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.com.model.User;
@RestController
public class UserController {
	@PostMapping("/register")
	public User register(@RequestBody User user) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cafe_test3");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		
		
		try {
			tx.begin();
			
			em.persist(user);
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return user;
	}
	
	@GetMapping("/idInfo")
	public User getIdInfo(@RequestParam("index") Long index, @RequestParam("name") String name,
			@RequestParam("password") String password) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cafe_test3");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			User selectedUser = em.find(User.class,index);
			
			return selectedUser;
			
			
		} catch (Exception e) {
			System.out.println("해당 아이디가 존재하지 않습니다.");
		}
		
		return null;
	}
	
	@GetMapping("/pwdEdit")
	public User setPwd(@RequestParam("id") Long index,@RequestParam("password") String password, @RequestParam("updatePwd") String updatePwd ) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cafe_test3");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			User user = em.find(User.class,index);
			
			user.setPassword(updatePwd);
			
			em.persist(user);
			tx.commit();
			
			return user;
		} catch (Exception e) {
			System.out.println("수정실패");
		}
		return null;
	}
	
	@DeleteMapping("/delUser")
	public void delUser(@RequestParam("index") Long index, @RequestParam("id") String id,@RequestParam("password") String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cafe_test3");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			User user = em.find(User.class,index);
			
			if (user.getId() != id && user.getPassword() != password) {
				em.remove(user);
				
			} else {
				System.out.println("아이디, 비밀번호가 틀립니다.");
			}
			
		} catch (Exception e) {
			System.out.println("해당 아이디가 존재하지 않습니다.");
		}
		tx.commit();
	}
}