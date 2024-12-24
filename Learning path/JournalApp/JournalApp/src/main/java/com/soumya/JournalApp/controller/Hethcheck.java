package com.soumya.JournalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heath")
public class Hethcheck {

    @GetMapping
    public String heacthCheck() {
        return "ok";
    }
}
