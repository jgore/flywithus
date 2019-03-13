package tech.lideo.flywithus.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import tech.lideo.flywithus.FlyWithUsApplication;
import tech.lideo.flywithus.controller.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private List<UserDto> userDtoList = new ArrayList<>();

    @Override
    public UserDto get(String email) {

        UserDto userFromDb = userDtoList.stream().
                filter(dto -> dto.getEmail().equals(email))
                .findAny()
                .orElse(null);

        if (userFromDb == null) {
            return null;
        }


        return copyUserDto(userFromDb);
    }

    @Override
    public void deleteAll() {
        userDtoList.clear();
    }

    @Override
    public int getCount() {
        return userDtoList.size();
    }

    @Override
    public UserDto create(UserDto userDto) {
        UserDto userFromDb = get(userDto.getEmail());

        if (userFromDb != null) {
            throw new IllegalArgumentException("user already exists");
        }

        UserDto copy = copyUserDto(userDto);

        userDtoList.add(copy);
        logger.info("created user with email" + copy.getEmail() );
        return userDto;
    }

    private UserDto copyUserDto(UserDto userDto) {
        String jsonUserDto = FlyWithUsApplication.gson.toJson(userDto);
        return FlyWithUsApplication.gson.fromJson(jsonUserDto, UserDto.class);
    }

}
