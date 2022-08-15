package com.gft.microservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gft.microservice.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
