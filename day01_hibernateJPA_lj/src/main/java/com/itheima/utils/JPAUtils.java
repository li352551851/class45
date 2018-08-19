package com.itheima.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtils {
	// JPA的实体管理器工厂
	private static EntityManagerFactory factory;
	
	static {
		factory=Persistence.createEntityManagerFactory("myJPAUnit");
	}

	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

	
	
}
