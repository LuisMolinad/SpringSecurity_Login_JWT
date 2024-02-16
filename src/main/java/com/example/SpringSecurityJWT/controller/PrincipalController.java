package com.example.SpringSecurityJWT.controller;

import com.example.SpringSecurityJWT.controller.request.CreateUserDTO;
import com.example.SpringSecurityJWT.models.ERole;
import com.example.SpringSecurityJWT.models.RoleEntity;
import com.example.SpringSecurityJWT.models.UserEntity;
import com.example.SpringSecurityJWT.repositories.UserRepository;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PrincipalController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/hello")
    public String hello(){
        return "hello world not secured";
    }

    @GetMapping("/helloSecured")
    public String helloSecured(){
        return "Hello world secured";
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser( @Valid @RequestBody CreateUserDTO createUserDTO){

        Set<RoleEntity> roles = createUserDTO.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .name(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());
        UserEntity userEntity=UserEntity.builder()
                .username(createUserDTO.getUsername())
                .password(passwordEncoder.encode(createUserDTO.getPassword()))
                .email(createUserDTO.getEmail())
                .roles(roles)
                .build();

        userRepository.save(userEntity);

        return ResponseEntity.ok(userEntity);
    }
    
    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam String id){
        userRepository.deleteById(Long.parseLong(id));
        return "se ha borrado el user con id ".concat(id);
    }


}
