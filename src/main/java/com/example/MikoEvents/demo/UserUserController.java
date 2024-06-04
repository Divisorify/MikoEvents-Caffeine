package com.example.MikoEvents.demo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/user")
public class UserUserController {


    @GetMapping
    public String get() {
        return "GET:: user controller";
    }

    @PostMapping
    public String post() {
        return "POST:: user controller";
    }

    @PutMapping
    public String put() {
        return "PUT:: user controller";
    }

    @DeleteMapping
    public String delete() {
        return "DELETE:: user controller";
    }
}
