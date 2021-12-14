package org.matusikl.service;

import org.matusikl.model.Laptop;
import org.springframework.stereotype.Service;

@Service
public class LaptopService {

    public Laptop getLaptop(){
        Laptop laptop = new Laptop();
        laptop.setIdLaptop(1);
        laptop.setNameLaptop("Asus123");
        laptop.setPasswordLaptop("password");
        laptop.setBrandLaptop("Asus");
        laptop.setLoginLaptop("New");
        return laptop;
    }
}
