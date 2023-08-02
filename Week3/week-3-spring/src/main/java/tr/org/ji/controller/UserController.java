package tr.org.ji.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.org.ji.DTO.CreateUserRequestDTO;
import tr.org.ji.DTO.UpdateUserRequestDTO;
import tr.org.ji.DTO.UserResponseDTO;
import tr.org.ji.service.UserService;
import tr.org.ji.service.impl.UserCachePrototype;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("users")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final ApplicationContext applicationContext;

    UserController(UserService userService, ApplicationContext applicationContext){
        this.userService = userService;
        this.applicationContext = applicationContext;
    }

    @GetMapping()
    public ResponseEntity<List<UserResponseDTO>> getUsers(){
        return  new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@Valid @PathVariable Long id){
        UserResponseDTO responseDTO = userService.getUserById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/by-hql/{id}")
    public ResponseEntity<UserResponseDTO> getUserByHql(@Valid @PathVariable Long id){
        return new ResponseEntity<>(userService.getUserByIdHql(id), HttpStatus.OK);
    }

    @GetMapping("/by-sql/{id}")
    public ResponseEntity<UserResponseDTO> getUserBySql(@Valid @PathVariable Long id){
        return new ResponseEntity<>(userService.getUserByIdSql(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UserResponseDTO> saveUser(@Valid @RequestBody CreateUserRequestDTO dto){
        UserResponseDTO responseDTO = userService.saveUser(dto);
//        var singletonBean = userService;
//        var prototypeBean = applicationContext.getBean(UserCachePrototype.class);
//
//        singletonBean.saveUser(dto);
//        prototypeBean.saveUser(dto);
//        return new ResponseEntity<>(Map.of("singleton", singletonBean.getUsers(), "prototype", prototypeBean.getUsers()), HttpStatus.OK);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO>  updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequestDTO dto){
        UserResponseDTO responseDTO = userService.updateUser(id, dto);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDTO> deleteUser(@Valid @PathVariable Long id){
       UserResponseDTO responseDTO = userService.deleteUser(id);
       return  new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/all-by-username")
    public ResponseEntity<List<UserResponseDTO>> getAllUsersByUsername(@RequestParam(name = "username" , defaultValue = "") String username){
        return new ResponseEntity<>(userService.getAllUsersByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/by-username")
    public ResponseEntity<UserResponseDTO> getUserByUsername(@RequestParam(name = "username" , defaultValue = "") String username){
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/by-pagination")
    public ResponseEntity<Page<UserResponseDTO>> getUsersWithJpaPagination(@RequestParam( defaultValue = "0") int pageNumber, @RequestParam( defaultValue = "2") int pageSize){
        return new ResponseEntity<>(userService.getUsers(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/by-dao-pagination")
    public ResponseEntity<List<UserResponseDTO>> getUsersWithDaoPagination(@RequestParam( defaultValue = "0") int pageNumber, @RequestParam( defaultValue = "2") int pageSize){
        return new ResponseEntity<>(userService.getUsersWithDao(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/users-with-role")
    public ResponseEntity<List<UserResponseDTO>> getUsersWithRole(@RequestParam( defaultValue = "USER") String role){
        return new ResponseEntity<>(userService.getUsersWithRole("ROLE_"+role), HttpStatus.OK);
    }
}
