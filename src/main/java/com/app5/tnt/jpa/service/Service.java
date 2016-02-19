package com.app5.tnt.jpa.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Service {

	private final EntityManagerFactory emf ;
	
	EntityManager getNewEntityManager() {

		return emf.createEntityManager();
	}
	
	Service (){
		emf = Persistence.createEntityManagerFactory("remote-server");
	}
	
	Service (String persistenceUnit ){
		emf = Persistence.createEntityManagerFactory( persistenceUnit );
	}
	
	public <Entity> void commit(CommitOperation commitOperation, Entity entity) {
		EntityManager em = getNewEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		commitOperation.apply(em, entity);
		tx.commit();
	}

	public <Entity> void commitAll(CommitOperation commitOperation, Collection<Entity> entitis) {
		EntityManager em = getNewEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		for (Entity entity : entitis)
			commitOperation.apply(em, entity);
		tx.commit();
	}

	public <Entity> Entity getSingleResult(Class<Entity> entityClass, String queryName, Map<String, Object> queryParameter) {
		Entity result = null;
		TypedQuery<Entity> query = getNewEntityManager().createNamedQuery(queryName, entityClass);

		if (queryParameter != null)
			for (String parameterName : queryParameter.keySet())
				query.setParameter(parameterName, queryParameter.get(parameterName));

		try {
			result = query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return result;
	}

	public <Entity> List<Entity> getResultList(Class<Entity> entityClass, String queryName, Map<String, Object> queryParameter) {
		List<Entity> result = null;
		TypedQuery<Entity> query = getNewEntityManager().createNamedQuery(queryName, entityClass);

		if (queryParameter != null)
			for (String parameterName : queryParameter.keySet())
				query.setParameter(parameterName, queryParameter.get(parameterName));

		try {
			result = query.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public <Entity> Entity findById (Class<Entity> entityClass, Object Id){
		return getNewEntityManager().find(entityClass, Id);
	}
}