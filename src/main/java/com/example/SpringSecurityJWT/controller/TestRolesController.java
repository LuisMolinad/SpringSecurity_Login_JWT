package com.example.SpringSecurityJWT.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRolesController {

    @GetMapping("/accessAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public String accessAdmin(){
        return "Hola has accedido con Rol de Admin";
    }

    @GetMapping("/accessUser")
    @PreAuthorize("hasRole('USER')")
    public String accessUser(){
        return "Hola has accedido con Rol de USER";
    }

    @GetMapping("/accessInvited")
    @PreAuthorize("hasRole('INVITED')")
    public String accessInvited(){
        return "Hola has accedido con Rol de INVITED";
    }

}
