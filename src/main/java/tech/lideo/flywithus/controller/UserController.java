package tech.lideo.flywithus.controller;

import org.springframework.web.bind.annotation.*;
import tech.lideo.flywithus.controller.dto.UserDto;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @RequestMapping(value = "/{login}", method = RequestMethod.GET)
    public UserDto get(@PathVariable String login) {
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public UserDto create(@RequestBody UserDto userDto) {
        return null;
    }


}
