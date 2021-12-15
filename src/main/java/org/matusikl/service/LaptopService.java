package org.matusikl.service;

import org.matusikl.model.Laptop;
import org.matusikl.repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaptopService {

    LaptopRepository laptopRepository;

    public LaptopService(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
        //podobno lepiej https://stackoverflow.com/questions/7142622/what-is-the-difference-between-inject-and-autowired-in-spring-framework-which
    }

    public Laptop getLaptop(){
        Laptop laptop = laptopRepository.findById(1).orElse(null);
        return laptop;
    }
}
