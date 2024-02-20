package com.example.springwebfluxpostgres.controller;

import com.example.springwebfluxpostgres.dto.UserDto;
import com.example.springwebfluxpostgres.model.User;
import com.example.springwebfluxpostgres.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    Mono<User> create(@RequestBody Mono<UserDto> userDto) {
        return userService.create(userDto);
    }

    @PostMapping("/badreq/")
    Mono<ResponseEntity> createWithBadReq(@RequestBody Mono<UserDto> userDto) {
        return userService.create(userDto).map(user -> ResponseEntity.badRequest().body(new User("BAD_REQ","BAD_REQ")));
    }

    @PostMapping("/badreqnobody/")
    Mono<ResponseEntity> createWithoutBody(@RequestBody Mono<UserDto> userDto) {
        return userService.create(userDto).map(user -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/servererr/")
    Mono<ResponseEntity> createWithServerErr(@RequestBody Mono<UserDto> userDto) {
        return userService.create(userDto).map(user -> ResponseEntity.internalServerError().body(new User("SERVER_ERR","SERVER_ERR")));
    }

    @PostMapping("/servererrnobody/")
    Mono<ResponseEntity> createWithServerErrNoBody(@RequestBody Mono<UserDto> userDto) {
        return userService.create(userDto).map(user -> ResponseEntity.internalServerError().build());
    }


    @GetMapping("/{userId}")
    Mono<ResponseEntity<User>> retrieve(@PathVariable int userId) {
        return userService.retrieve(userId)
                .map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());

        /*return userService.retrieve(userId).map(ResponseEntity::ok).defaultIfEmpty(
                ResponseEntity.badRequest().body(new User("bad request","bad request")));*/
    }

    @PutMapping("/{userId}")
    Mono<ResponseEntity<User>> update(@PathVariable int userId, @RequestBody Mono<UserDto> userDto) {
        return userService.update(userId, userDto).map(ResponseEntity::ok).defaultIfEmpty(
                ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{userId}")
    Mono<Void> delete(@PathVariable int userId) {
        return userService.delete(userId);
    }

    @GetMapping("/")
    Flux<User> list() {
        return userService.list();
    }
}
