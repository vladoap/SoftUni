package com.example.mobilelele_web.web;

import com.example.mobilelele_web.model.binding.UserLoginBindingDto;
import com.example.mobilelele_web.model.binding.UserRegisterBindingDto;
import com.example.mobilelele_web.model.service.UserLoginServiceDto;
import com.example.mobilelele_web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/register")
    public String register() {
        return "auth-register";
    }

    @PostMapping("users/register")
    public String registerPost(UserRegisterBindingDto userRegisterBindingDto) {
        //TODO:


        return "redirect:/";
    }

    @GetMapping("/users/login")
    public String login() {
        return "auth-login";
    }

    @PostMapping("users/login")
    public String login(UserLoginBindingDto userLoginBindingDto) {


        boolean loginSuccessful = userService
                .login(new UserLoginServiceDto()
                        .setRawPassword(userLoginBindingDto.getPassword())
                        .setUsername(userLoginBindingDto.getUsername()));

        LOGGER.info("User tried to login. User name {}, user password {}, successful {}",
                userLoginBindingDto.getUsername(),
                userLoginBindingDto.getPassword(),
                loginSuccessful);
        return "redirect:/";
    }


}
