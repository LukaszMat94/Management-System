package org.matusikl.controller;

import org.matusikl.model.Laptop;
import org.matusikl.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FrontController {

    @Autowired
    LaptopService laptopService;

    @GetMapping(value = "/welcome", produces = "application/json")
    public Laptop welcomePage(){

        return laptopService.getLaptop();
    }
}
