package com.itheima.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Test;

import com.itheima.domain.Customer;
import com.itheima.utils.JPAUtils;

public class TestJPA {
	
	@Test
	public void Test1() {
		// JPA的实体管理器工厂
		// 注意：该方法参数必须和persistence.xml中persistence-unit标签name属性取值一致
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJPAUnit");
		// 创建实体管理类
		EntityManager em = factory.createEntityManager();
		// 获取事务对象
		EntityTransaction tx = em.getTransaction();
		// 开启事务
		tx.begin();
		Customer customer = new Customer();
		customer.setCustName("爱国者电子科技有限公司");
		// 保存操作
		em.persist(customer);
		// 提交事务
		tx.commit();
		// 释放资源
		em.close();
		
	}
	
	@Test
	public void testSave() {
		EntityManager em = JPAUtils.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Customer customer = new Customer();
		customer.setCustName("匡峥洗脚城");
		em.persist(customer);
		tx.commit();
		em.close();
	}

	@Test
	public void testFindById() {
		EntityManager em = JPAUtils.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Customer c = em.find(Customer.class, 2L);
		System.out.println(c.getCustName());
		tx.commit();
		
		em.close();
	}
	
	
	@Test
	public void testUpdate() {
		EntityManager em = JPAUtils.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Customer c = em.find(Customer.class, 2L);
		c.setCustAddress("黑马顺义");
		em.merge(c);
		
		tx.commit();
		em.close();
	}
	
	@Test
	public void testDelete() {
		EntityManager em = JPAUtils.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Customer c = em.find(Customer.class, 1L);
		em.remove(c);
		
		tx.commit();
		em.close();
	}
	
	
	@Test
	public void testGetOne() {
		EntityManager em = JPAUtils.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Customer c = em.getReference(Customer.class, 2L);
		System.out.println(c.getCustName());
		
		tx.commit();
		em.close();
	}
	
	
	@Test
	public void testFindAll() {
		EntityManager em = JPAUtils.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		//编写JPQL语句 ：java Persisence Query Language
		//from后面跟的是类名
		Query query = em.createQuery("from Customer");
		List<Customer> list = query.getResultList();
		for (Customer c : list) {
			System.out.println(c.getCustName());
		}
		
		tx.commit();
		em.close();
	}
	
	
	
	@Test
	public void testFindByManyCondition() {
		EntityManager em = JPAUtils.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		//编写JPQL语句 ：java Persisence Query Language
		//from后面跟的是类名
		Query query = em.createQuery("from Customer where custName like ? and custIndustry=?");
		query.setParameter(1, "%集团%");
		query.setParameter(2, "电商");
		List<Customer> list = query.getResultList();
		for (Customer c : list) {
			System.out.println(c.getCustName());
		}
		
		tx.commit();
		em.close();
	}
	
	
	@Test
	public void testOrderBy() {
		EntityManager em = JPAUtils.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		//编写JPQL语句 ：java Persisence Query Language
		//from后面跟的是类名
		Query query = em.createQuery("from Customer order by custId desc");
		
		List<Customer> list = query.getResultList();
		for (Customer c : list) {
			System.out.println(c.getCustName());
		}
		
		tx.commit();
		em.close();
	}
	
	@Test
	public void testByPage() {
		EntityManager em = JPAUtils.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		//编写JPQL语句 ：java Persisence Query Language
		//from后面跟的是类名
		Query query = em.createQuery("from Customer");
		query.setFirstResult(0);
		query.setMaxResults(2);
		List<Customer> list = query.getResultList();
		for (Customer c : list) {
			System.out.println(c.getCustName());
		}
		
		tx.commit();
		em.close();
	}
	
}
