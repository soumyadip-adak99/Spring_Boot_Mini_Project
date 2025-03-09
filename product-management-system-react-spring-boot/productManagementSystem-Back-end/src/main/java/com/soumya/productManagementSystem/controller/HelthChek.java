package com.soumya.productManagementSystem.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthcheck")
public class HelthChek {

    @GetMapping
    public String helthChek() {
        return "Ok";
    }
}
