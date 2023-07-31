package tr.org.ji.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.org.ji.DTO.CreateUserRequestDTO;
import tr.org.ji.DTO.UpdateUserRequestDTO;
import tr.org.ji.DTO.UserResponseDTO;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping()
    public String getUsers(){
        int a = 1/0;
        log.info("All users returned");
        return "all users";
    }

    @GetMapping("/{id}")
    public String getUser(@Valid @PathVariable Long id){
        log.info("User obtained with id : " +id);
        return "user with id : " + id;
    }

    @PostMapping()
    public ResponseEntity<String> saveUser(@Valid @RequestBody CreateUserRequestDTO dto){
        log.info("User saved : " +dto.toString());
        return new ResponseEntity<>("Created User : "+dto.toString(), HttpStatus.OK);
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
