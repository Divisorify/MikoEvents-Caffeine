package com.example.MikoEvents.user;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserDto mapToDto(User user);

	List<UserDto> mapToDto(List<User> userList);
}
