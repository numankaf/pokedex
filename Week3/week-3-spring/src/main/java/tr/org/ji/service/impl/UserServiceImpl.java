package tr.org.ji.service.impl;


import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tr.org.ji.DTO.CreateUserRequestDTO;
import tr.org.ji.DTO.UpdateUserRequestDTO;
import tr.org.ji.DTO.UserResponseDTO;
import tr.org.ji.Entity.Role;
import tr.org.ji.Entity.User;
import tr.org.ji.Entity.UserDetailsImpl;
import tr.org.ji.repository.RoleRepository;
import tr.org.ji.repository.UserDao;
import tr.org.ji.repository.UserRepository;
import tr.org.ji.service.UserService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Primary
public class UserServiceImpl implements UserService , UserDetailsService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository, UserDao userDao, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        UserResponseDTO responseDTO = new UserResponseDTO();
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No user found with id : " + id));
        responseDTO.setId(user.getId());
        responseDTO.setUsername(user.getUsername());

        return responseDTO;
    }

    @Override
    public UserResponseDTO saveUser(CreateUserRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        var roleUserOpt = roleRepository.findByName("ROLE_USER");
        roleUserOpt.ifPresent((userRole)->user.setRoles(Set.of(userRole)));
        userRepository.save(user);
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setUsername(user.getUsername());
        responseDTO.setId(user.getId());
        responseDTO.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
        return responseDTO;
    }

    @Override
    public List<UserResponseDTO> getUsers() {
        return userRepository.findAll().stream().map(user -> {
            UserResponseDTO dto = new UserResponseDTO();
            dto.setId(user.getId());
            dto.setUsername(user.getUsername());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<UserResponseDTO> getUsersWithRole(String role) {
        return userRepository.findByRoles_NameIn(List.of(role)).stream().map(user -> {
            UserResponseDTO dto = new UserResponseDTO();
            dto.setId(user.getId());
            dto.setUsername(user.getUsername());
            dto.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO updateUser(Long id, UpdateUserRequestDTO dto) {
        User userToUpdate = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No user found with id : " + id));
        userToUpdate.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(userToUpdate);
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setUsername(userToUpdate.getUsername());
        responseDTO.setId(userToUpdate.getId());
        return responseDTO;


    }

    @Override
    public UserResponseDTO deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No user found with id : " + id));
        user.setActive(!user.getActive());
        userRepository.save(user);
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(id);
        responseDTO.setUsername(user.getUsername());
        return responseDTO;
    }

    @Override
    public List<UserResponseDTO> getAllUsersByUsername(String username) {
        return userRepository.findAllByUsernameStartsWithAndActiveIsTrueOrderByCreatedDateDesc(username).stream().map(t -> {
            UserResponseDTO responseDTO = new UserResponseDTO();
            responseDTO.setId(t.getId());
            responseDTO.setUsername(t.getUsername());
            return responseDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("Invlaid username"));
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setUsername(user.getUsername());
        return responseDTO;
    }

    @Override
    public Page<UserResponseDTO> getUsers(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        var page = userRepository.findAll(pageable);
        var result = page.stream().map(t -> {
            UserResponseDTO responseDTO = new UserResponseDTO();
            responseDTO.setId(t.getId());
            responseDTO.setUsername(t.getUsername());
            return responseDTO;
        }).collect(Collectors.toList());
        return new PageImpl<>(result, pageable, page.getTotalElements());
    }

    @Override
    public UserResponseDTO getUserByIdHql(Long id) {
        User user = userRepository.getByIdHql(id).orElseThrow(() -> new IllegalArgumentException("Id not valid"));
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setUsername(user.getUsername());
        return responseDTO;
    }

    @Override
    public UserResponseDTO getUserByIdSql(Long id) {
        User user = userRepository.getByIdSql(id).orElseThrow(() -> new IllegalArgumentException("Id not valid"));
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setUsername(user.getUsername());
        return responseDTO;
    }

    @Override
    public List<UserResponseDTO> getUsersWithDao(int pageNumber, int pageSize) {
        return userDao.getUsers(pageNumber,pageSize).stream().map(t -> {
            UserResponseDTO responseDTO = new UserResponseDTO();
            responseDTO.setId(t.getId());
            responseDTO.setUsername(t.getUsername());
            return responseDTO;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(()->new IllegalArgumentException(""));
        Hibernate.initialize(user.getRoles());
        return new UserDetailsImpl(user);
    }
}
