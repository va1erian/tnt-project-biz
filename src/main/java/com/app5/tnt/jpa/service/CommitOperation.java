package com.app5.tnt.jpa.service;
import javax.persistence.EntityManager;

public enum CommitOperation {

	Persist {
		@Override
		<Entity> void apply(EntityManager em, Entity entity) {
			em.persist(entity);
		}
	}, 
	Update {
		@Override
		<Entity> void apply(EntityManager em, Entity entity) {
			em.merge(entity);
		}
	},
	Delete {
		@Override
		<Entity> void apply(EntityManager em, Entity entity) {
			em.remove(entity);
			
		}
	}
	;
	
	abstract <Entity> void apply(EntityManager em , Entity entity);
}
