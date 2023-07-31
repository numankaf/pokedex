package tr.org.ji.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateUserRequestDTO {
    @NotBlank(message = "Username cannot be null")
    @Size(min = 5, max = 20, message = "Username should have at least 5 characters  and at most 20 characters")
    private String username;

    @NotBlank(message = "Password cannot be null")
    @Size(min = 5, max = 20, message = "Password should have at least 5 characters  and at most 20 characters")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CreateUserRequestDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
