package com.gcaa.fplmb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gcaa.fplmb.entity.ConnectionInterfaceEntity;

@Repository
public interface ConnectionRepository extends CrudRepository<ConnectionInterfaceEntity, Long> {

}
