package tr.org.ji.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
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
    public String getUser(@Valid @PathVariable Long id){
        log.info("User obtained with id : " +id);
        return "user with id : " + id;
    }

    @PostMapping()
    public ResponseEntity<Map<?,?>> saveUser(@Valid @RequestBody CreateUserRequestDTO dto){
        UserResponseDTO responseDTO = userService.saveUser(dto);
        var singletonBean = userService;
        var prototypeBean = applicationContext.getBean(UserCachePrototype.class);

        singletonBean.saveUser(dto);
        prototypeBean.saveUser(dto);
        return new ResponseEntity<>(Map.of("singleton", singletonBean.getUsers(), "prototype", prototypeBean.getUsers()), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<String>  updateUser(@Valid @RequestBody UpdateUserRequestDTO dto){
        log.info("User updated : "+ dto.toString());
        return new ResponseEntity<>("Updated User : "+dto.toString(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@Valid @PathVariable Long id){
        log.info("User deleted with id : " +id);
        return "user deleted with id : " +id;
    }

}
