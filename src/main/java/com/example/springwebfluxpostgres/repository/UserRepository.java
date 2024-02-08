package com.example.springwebfluxpostgres.repository;

import com.example.springwebfluxpostgres.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User,Integer> {
}
