package com.example.controller;

import com.example.entity.Crypto;
import com.example.service.CryptoService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CryptoService cryptoService;

    @PostMapping("/new-user")
    public String saveUser(@RequestParam(name = "name") String name, @RequestParam(name = "id") Long id) {
        Crypto crypto = cryptoService.getCryptoById(id);
        userService.saveUser(name, crypto.getPrice(), id);
        return "Success registration";
    }
}
