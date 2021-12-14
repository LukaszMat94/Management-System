package org.matusikl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FrontController {

    @GetMapping("/welcome")
    public String welcomePage(){
        return "Welcome Page Hello World";
    }
}
