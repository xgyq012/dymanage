package com.base.orm.core;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DBUtil {

	private static EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		DBUtil.entityManager = entityManager;
	}

//		public DBUtil(EntityManagerFactory entityManagerFactory) {
//		DBUtil.entityManager = entityManagerFactory.createEntityManager();
//	}

	public static <T> T find(Class<T> entityClass, Object primaryKey) {
		return DBUtil.entityManager.find(entityClass, primaryKey);
	}

}
