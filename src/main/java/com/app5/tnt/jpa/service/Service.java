package com.app5.tnt.jpa.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface Service {
	
	public <Entity> void commit(CommitOperation commitOperation, Entity entity);

	public <Entity> void commitAll(CommitOperation commitOperation, Collection<Entity> entitis);

	public <Entity> Entity getSingleResult(Class<Entity> entityClass, String queryName, Map<String, Object> queryParameter);

	public <Entity> List<Entity> getResultList(Class<Entity> entityClass, String queryName, Map<String, Object> queryParameter);
	
	public <Entity> Entity findById (Class<Entity> entityClass, Object Id);
	
}
