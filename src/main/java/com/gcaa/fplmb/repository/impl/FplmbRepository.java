package com.gcaa.fplmb.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gcaa.fplmb.entity.AftnMessageEntity;
import com.gcaa.fplmb.entity.MessageQueueEntity;

@Component
public class FplmbRepository {

	@Autowired
	private EntityManagerFactory emf;
	
	@Autowired
	private EntityManager entityManager;
	
	@org.springframework.transaction.annotation.Transactional
	public AftnMessageEntity saveAftnMessage(AftnMessageEntity entity){
		
		return entityManager.merge(entity);
		
	}
	
}
