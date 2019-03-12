package tech.lideo.flywithus.repository;

import org.springframework.stereotype.Repository;
import tech.lideo.flywithus.FlywithusApplication;
import tech.lideo.flywithus.controller.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private List<UserDto> userDtoList = new ArrayList<>();

    @Override
    public UserDto get(String login) {

        UserDto userFromDb = userDtoList.stream().
                filter(dto -> dto.getLogin().equals(login))
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
        UserDto userFromDb = get(userDto.getLogin());

        if (userFromDb != null) {
            throw new IllegalArgumentException("user already exists");
        }

        UserDto copyUserDto = copyUserDto(userDto);

        userDtoList.add(copyUserDto);
        return userDto;
    }

    private UserDto copyUserDto(UserDto userDto) {
        String jsonUserDto = FlywithusApplication.gson.toJson(userDto);
        return FlywithusApplication.gson.fromJson(jsonUserDto, UserDto.class);
    }

}
