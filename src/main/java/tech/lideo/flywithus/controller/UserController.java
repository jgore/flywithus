package tech.lideo.flywithus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.lideo.flywithus.controller.dto.UserDto;
import tech.lideo.flywithus.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public UserDto create(@Valid @RequestBody UserDto userDto) {
        return userService.create(userDto);
    }


}
