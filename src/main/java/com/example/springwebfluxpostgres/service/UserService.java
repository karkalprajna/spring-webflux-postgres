package com.example.springwebfluxpostgres.service;

import com.example.springwebfluxpostgres.dto.UserDto;
import com.example.springwebfluxpostgres.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<User> create(Mono<UserDto> userDto);

    Mono<User> retrieve(int userId);

    Mono<User> update(int userId, Mono<UserDto> userDto);

    Mono<Void> delete(int userId);

    Flux<User> list();
}
