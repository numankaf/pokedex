package tr.org.ji.service;

import tr.org.ji.DTO.CreateUserRequestDTO;
import tr.org.ji.DTO.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO saveUser(CreateUserRequestDTO dto);
    List<UserResponseDTO> getUsers();
}
