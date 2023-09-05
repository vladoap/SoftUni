package com.example.mobilelele_web.web;

import com.example.mobilelele_web.users.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    private final CurrentUser currentUser;

    public LogoutController(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @GetMapping("/users/logout")
    public String logout() {
        currentUser.clean();
        return "redirect:/";
    }
}
