package com.example.springwebfluxpostgres.service;

import com.example.springwebfluxpostgres.dto.UserDto;
import com.example.springwebfluxpostgres.model.User;
import com.example.springwebfluxpostgres.repository.UserRepository;
import com.example.springwebfluxpostgres.utils.UserUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<User> create(Mono<UserDto> userDto) {
        return userDto.map(UserUtils::toUser).flatMap(repository::save);
    }

    @Override
    public Mono<User> retrieve(int userId) {
        return repository.findById(userId);
    }

    @Override
    public Mono<User> update(int userId, Mono<UserDto> userDto) {
        return repository.findById(userId)
                .flatMap(user -> userDto
                        .map(UserUtils::toUser)
                        .doOnNext(u -> u.setId(userId)))
                .flatMap(repository::save);
    }

    @Override
    public Mono<Void> delete(int userId) {
        return repository.deleteById(userId);
    }

    @Override
    public Flux<User> list() {
        return repository.findAll();
    }
}
