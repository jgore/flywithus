package tech.lideo.flywithus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.lideo.flywithus.controller.dto.UserDto;
import tech.lideo.flywithus.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto create(UserDto userDto) {
        return userRepository.create(userDto);
    }

    @Override
    public UserDto get(String login) {
        return userRepository.get(login);
    }
}
