package tech.lideo.flywithus.repository;

import tech.lideo.flywithus.controller.dto.UserDto;

public interface UserRepository {

    UserDto create (UserDto userDto);
    UserDto get ( String login);

    void deleteAll();
    int getCount();

}
