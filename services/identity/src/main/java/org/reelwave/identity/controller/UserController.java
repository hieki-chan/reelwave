package org.reelwave.identity.controller;

import org.reelwave.identity.dto.RegisterRequest;
import org.reelwave.identity.dto.RegisterUserRespond;
import org.reelwave.identity.entity.User;
import org.reelwave.identity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public RegisterUserRespond register(@RequestBody RegisterRequest registerRequest)
    {
        var user = userService.register(
                registerRequest.getUsername(),
                registerRequest.getEmail(),
                registerRequest.getPassword());
        return new RegisterUserRespond();
    }
}
