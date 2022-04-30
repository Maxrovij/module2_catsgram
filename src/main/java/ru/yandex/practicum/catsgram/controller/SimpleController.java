package ru.yandex.practicum.catsgram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
public class SimpleController {

    @GetMapping("/home")
    public String homePage() {
        return "Котограм";
    }
}
