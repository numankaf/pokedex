package tr.org.ji.service.impl;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tr.org.ji.DTO.CreateUserRequestDTO;
import tr.org.ji.DTO.UserResponseDTO;
import tr.org.ji.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service("userCacheSingleton")
@Primary
public class UserCacheSingleton implements UserService {
    private final Logger logger = LoggerFactory.getLogger(UserCacheSingleton.class);
    public Map<String, CreateUserRequestDTO> users;

    @PostConstruct
    public void init(){
        logger.info("Singleton bean created");
        users = new TreeMap<>();
    }

    @PreDestroy
    public void destroy(){
        logger.info("Singleton bean destroyed");
    }


    @Override
    public UserResponseDTO saveUser(CreateUserRequestDTO dto) {
        users.put(dto.getUsername(), dto);
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setUsername(dto.getUsername());
        return responseDTO;
    }

    @Override
    public List<UserResponseDTO> getUsers() {
        return users.values().stream().map( k->{
            UserResponseDTO responseDTO = new UserResponseDTO();
            responseDTO.setUsername(k.getUsername());
            return responseDTO;
        }).collect(Collectors.toList());
    }
}
