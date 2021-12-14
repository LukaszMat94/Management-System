package org.matusikl.service;

import org.matusikl.model.Laptop;
import org.matusikl.repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaptopService {

    @Autowired
    LaptopRepository laptopRepository;

    public Laptop getLaptop(){
        Laptop laptop = laptopRepository.findById(1).orElse(null);
        return laptop;
    }
}
