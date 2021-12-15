package org.matusikl.service;

import org.matusikl.model.Laptop;
import org.matusikl.repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaptopService {

    LaptopRepository laptopRepository;

    @Autowired
    public LaptopService(LaptopRepository laptopRepository){
        this.laptopRepository = laptopRepository;
    }

    public Laptop getLaptop(){
        Laptop laptop = laptopRepository.findById(1).orElse(null);
        return laptop;
    }

    public List<Laptop> getLaptops(){
        List<Laptop> laptops = laptopRepository.findAll();
        return laptops;
    }

    public void addLaptop(Laptop laptop){
        laptopRepository.save(laptop);
    }

    public void deleteLaptop(Integer idLaptop){
        laptopRepository.deleteById(idLaptop);
    }

    public Laptop updateLaptop(Integer id, Laptop laptop){
        Laptop laptopDB = laptopRepository.findById(id).orElse(null);

        laptopDB.setBrandLaptop(laptop.getBrandLaptop());
        laptopDB.setNameLaptop(laptop.getNameLaptop());
        laptopDB.setLoginLaptop(laptop.getLoginLaptop());
        laptopDB.setPasswordLaptop(laptop.getPasswordLaptop());

        laptopRepository.save(laptopDB);
        return laptopDB;
    }
}
