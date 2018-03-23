package pl.noip.lolstats.lol.stats.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/login")
public class LoginController {

    @GetMapping
    public String hello(){
        return "Hello";
    }
}

