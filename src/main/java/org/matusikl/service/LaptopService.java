package org.matusikl.service;

import org.matusikl.exception.DataDuplicateException;
import org.matusikl.exception.DataNotFoundException;
import org.matusikl.model.Laptop;
import org.matusikl.repository.LaptopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LaptopService {

    LaptopRepository laptopRepository;
    private Logger logger = LoggerFactory.getLogger(LaptopService.class);

    @Autowired
    public LaptopService(LaptopRepository laptopRepository){
        this.laptopRepository = laptopRepository;
    }

    public Laptop getLaptop(Integer id){
        logger.debug("In LaptopService getLaptop() method");
        Laptop laptop = laptopRepository
                .findById(id)
                .orElseThrow(() -> {
                        DataNotFoundException exception = new DataNotFoundException(String.format("Laptop not exist in database with id: %d" ,id));
                        logger.error("Exception occured in getLaptop() id: {}", id, exception);
                        throw exception;
                });
        logger.info("Found laptop with id {}", id);
        return laptop;
    }

    public List<Laptop> getLaptops(){
        logger.debug("In LaptopService getLaptops() method");
        List<Laptop> laptops = laptopRepository.findAll();
        if(laptops.isEmpty()){
            DataNotFoundException exception =  new DataNotFoundException("There are no laptops in database");
            logger.error("Exception occured in getLaptops()", exception);
            throw exception;
        }
        else{
            logger.info("Found list of laptops");
            return laptops;
        }
    }

    public Laptop addLaptop(Laptop laptop){
        logger.debug("In LaptopService addLaptop() method");
        boolean laptopExist = laptopRepository.findLaptopByNameLaptop(laptop.getNameLaptop()).isPresent();
        if(laptopExist){
            DataDuplicateException exception = new DataDuplicateException(String.format("Cannot create laptop with the same laptop name: %s", laptop.getNameLaptop()));
            logger.error("Exception occured in addLaptop(): {}", exception);
            throw exception;
        }
        else {
            Laptop laptopDB = laptopRepository.save(laptop);
            logger.info("Laptop {} added successfully", laptopDB);
            return laptopDB;
        }
    }

    public void deleteLaptop(Integer idLaptop){
        logger.debug("In LaptopService deleteLaptop() method");
        if(laptopRepository.existsById(idLaptop)){
            laptopRepository.deleteById(idLaptop);
            logger.info("Laptop id {} deleted successfully", idLaptop);
        }
        else{
            DataNotFoundException exception = new DataNotFoundException(String.format("Cannot delete laptop with id: %d because this not exist in database", idLaptop));
            logger.error("Exception occured in deleteLaptop() id: {} ", idLaptop, exception);
            throw exception;
        }
    }

    public Laptop updateLaptop(Integer id, Laptop laptop) throws Exception {
        logger.debug("In LaptopService updateLaptop() method");
        Laptop laptopDB = laptopRepository
                .findById(id)
                .orElseThrow(() -> {
                        DataNotFoundException exception = new DataNotFoundException(String.format("Cannot find laptop with id: %d in database", id));
                        logger.error("Error occured in updateLaptop() findById()", exception);
                        throw exception;
                });

        boolean otherLaptopWithSameName = laptopRepository
                .findLaptopByNameLaptopAndOtherId(laptop.getNameLaptop(), id)
                .isPresent();

        if(!otherLaptopWithSameName){
            laptopDB.setBrandLaptop(laptop.getBrandLaptop());
            laptopDB.setNameLaptop(laptop.getNameLaptop());
            laptopDB.setLoginLaptop(laptop.getLoginLaptop());
            laptopDB.setPasswordLaptop(laptop.getPasswordLaptop());
            laptopRepository.save(laptopDB);
            logger.info("Laptop id {} updated successfully", id);
            return laptopDB;
        }
        else{
            DataDuplicateException exception = new DataDuplicateException(String.format("Laptop with name: %s already exist in database!", laptop.getNameLaptop()));
            logger.error("Error occured in updateLaptop() findLaptopByNameLaptopAndOtherId()", exception);
            throw exception;
        }
    }
}
