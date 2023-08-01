package tr.org.ji.service;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.data.domain.Page;
import tr.org.ji.DTO.CreateUserRequestDTO;
import tr.org.ji.DTO.UpdateUserRequestDTO;
import tr.org.ji.DTO.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO getUserById(Long id);

    UserResponseDTO saveUser(CreateUserRequestDTO dto);

    List<UserResponseDTO> getUsers();

    UserResponseDTO updateUser(Long id, UpdateUserRequestDTO dto);

    UserResponseDTO deleteUser(Long id);

    default List<UserResponseDTO> getAllUsersByUsername(String username) {
        throw new NotImplementedException();
    }

    default UserResponseDTO getUserByUsername(String username) {
        throw new NotImplementedException();
    }

    default Page<UserResponseDTO> getUsers(int pageNumber, int pageSize) {
        throw new NotImplementedException();
    }

    default UserResponseDTO getUserByIdHql(Long id) {
        throw new NotImplementedException();
    }

    default UserResponseDTO getUserByIdSql(Long id) {
        throw new NotImplementedException();
    }
}

