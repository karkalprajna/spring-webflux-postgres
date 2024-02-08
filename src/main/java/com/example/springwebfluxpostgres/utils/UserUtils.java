package com.example.springwebfluxpostgres.utils;

import com.example.springwebfluxpostgres.dto.UserDto;
import com.example.springwebfluxpostgres.model.User;

public class UserUtils {
    public static User toUser(UserDto userDto) {
        return new User(userDto.getFirstName(), userDto.getLastName());
    }

    public static UserDto toDto(User user) {
        return new UserDto(user.getFirstName(), user.getLastName());
    }
}
