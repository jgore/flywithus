package tech.lideo.flywithus.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import tech.lideo.flywithus.controller.dto.UserDto;
import tech.lideo.flywithus.repository.UserRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    public static final String TEST_LOGIN = "testLogin";
    public static final String TEST_PASSWORD = "testPassword";

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    public void get() {
        userRepository.get(TEST_LOGIN);
        verify(userRepository, times(1)).get(anyString());
    }

    @Test
    public void create() {
        UserDto userDto = new UserDto();
        userDto.setLogin(TEST_LOGIN);
        userDto.setPassword(TEST_PASSWORD);
        userServiceImpl.create(userDto);

        verify(userRepository, times(1)).create(any(UserDto.class));
    }
}