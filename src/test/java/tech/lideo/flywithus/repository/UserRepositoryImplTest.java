package tech.lideo.flywithus.repository;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.lideo.flywithus.controller.dto.UserDto;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryImplTest {

    private final String TEST_EMAIL = "testEmail";
    private final String TEST_PASSWORD = "testPassword";

    @Autowired
    private UserRepository userRepository;

    @After
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void getNotExistingUser__ShouldReturnNull() {
        UserDto userDto = userRepository.get(TEST_EMAIL);

        assertThat(userDto, equalTo(null));
    }

    @Test
    public void getExistingUser__shouldReturnUser() {

        UserDto userDto = new UserDto();
        userDto.setEmail(TEST_EMAIL);
        userDto.setPassword(TEST_PASSWORD);

        userRepository.create(userDto);
        UserDto userDtoFromDb = userRepository.get(TEST_EMAIL);

        assertThat(userDtoFromDb.getEmail(), equalTo(TEST_EMAIL));
        assertThat(userDtoFromDb.getPassword(), equalTo(TEST_PASSWORD));
    }

    @Test
    public void createNewUser__shouldCreateNewUser() {
        UserDto userDto = prepareUserDto();
        userRepository.create(userDto);

        assertThat( userRepository.getCount() ,equalTo(1));

    }

    private UserDto prepareUserDto()
    {
        UserDto userDto = new UserDto();
        userDto.setEmail(TEST_EMAIL);
        userDto.setPassword(TEST_PASSWORD);
        return userDto;
    }

}