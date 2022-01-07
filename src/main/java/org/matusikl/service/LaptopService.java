package org.matusikl.service;

import org.matusikl.exception.DataDuplicateException;
import org.matusikl.exception.DataNotFoundException;
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

    public Laptop getLaptop(Integer id){
        Laptop laptop = laptopRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException(String.format("Laptop not exist in database with id: %d" ,id)) {
        });
        return laptop;
    }

    public List<Laptop> getLaptops(){
        List<Laptop> laptops = laptopRepository.findAll();
        if(laptops.isEmpty()){
            throw new DataNotFoundException("There are no laptops in database");
        }
        else{
        return laptops;
        }
    }

    public Laptop addLaptop(Laptop laptop){

        boolean laptopExist = laptopRepository.findLaptopByNameLaptop(laptop.getNameLaptop()).isPresent();
        if(laptopExist){
            throw new DataDuplicateException(String.format("Cannot create laptop with the same laptop name: %s", laptop.getNameLaptop()));
        }
        else {
            Laptop addedLaptop = laptopRepository.save(laptop);
            return addedLaptop;
        }
    }

    public void deleteLaptop(Integer idLaptop){
        if(laptopRepository.existsById(idLaptop)){
            laptopRepository.deleteById(idLaptop);
        }
        else{
            throw new DataNotFoundException(String.format("Cannot delete laptop with id: %d because this not exist in database", idLaptop));
        }
    }

    public Laptop updateLaptop(Integer id, Laptop laptop) throws Exception {

        Laptop laptopDB = laptopRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException(String.format("Cannot find laptop with id: %d in database", id)));

        boolean otherLaptopWithSameName = laptopRepository
                .findLaptopByNameLaptopAndOtherId(laptop.getNameLaptop(), id)
                .isPresent();

        if(!otherLaptopWithSameName){
            laptopDB.setBrandLaptop(laptop.getBrandLaptop());
            laptopDB.setNameLaptop(laptop.getNameLaptop());
            laptopDB.setLoginLaptop(laptop.getLoginLaptop());
            laptopDB.setPasswordLaptop(laptop.getPasswordLaptop());
            laptopRepository.save(laptopDB);
            return laptopDB;
        }
        else{
            throw new DataDuplicateException(String.format("Laptop with name: %s already exist in database!", laptop.getNameLaptop()));
        }
    }
}
