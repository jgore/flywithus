package tech.lideo.flywithus.service;

import tech.lideo.flywithus.controller.dto.UserDto;

public interface UserService {

    UserDto create (UserDto userDto);
    UserDto getByEmail(String email);
}
