package org.matusikl.controller;

import org.matusikl.model.Laptop;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FrontController {

    @GetMapping(value = "/welcome", produces = "application/json")
    public Laptop welcomePage(){
        Laptop laptop = new Laptop();
        laptop.setIdLaptop(1);
        laptop.setNameLaptop("Asus123");
        laptop.setPasswordLaptop("password");
        laptop.setBrandLaptop("Asus");
        laptop.setLoginLaptop("New");
        return laptop;
    }
}
