package tech.lideo.flywithus.controller.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class UserDto {

    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(getEmail(), userDto.getEmail()) &&
                Objects.equals(getPassword(), userDto.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPassword());
    }
}
