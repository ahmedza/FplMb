package com.gcaa.fplmb.repository;

import org.springframework.data.repository.CrudRepository;

import com.gcaa.fplmb.entity.MessageQueueEntity;



public interface QueueRepository extends CrudRepository<MessageQueueEntity, Long> {
	
}
