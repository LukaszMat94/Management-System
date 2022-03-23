package org.matusikl.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.matusikl.dto.laptopdto.LaptopGetDto;
import org.matusikl.dto.laptopdto.LaptopPostDto;
import org.matusikl.exception.DataDuplicateException;
import org.matusikl.exception.DataNotFoundException;
import org.matusikl.exception.DataRelationException;
import org.matusikl.mapperinterface.LaptopIMapper;
import org.matusikl.model.Laptop;
import org.matusikl.repository.EmployeeRepository;
import org.matusikl.repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LaptopService {

    private final LaptopRepository laptopRepository;
    private final EmployeeRepository employeeRepository;
    private final LaptopIMapper laptopIMapper;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger = LogManager.getLogger(LaptopService.class);

    @Autowired
    public LaptopService(LaptopRepository laptopRepository,
                         EmployeeRepository employeeRepository,
                         LaptopIMapper laptopIMapper,
                         @Lazy PasswordEncoder passwordEncoder){
        this.laptopRepository = laptopRepository;
        this.employeeRepository = employeeRepository;
        this.laptopIMapper = laptopIMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public LaptopGetDto getLaptop(Integer id){
        logger.debug("In LaptopService getLaptop() method id: {}", id);
        Laptop laptop = laptopRepository
                .findById(id)
                .orElseThrow(() -> {
                        DataNotFoundException exception = new DataNotFoundException(String.format("Laptop not exist in database with id: %d" ,id));
                        logger.error("Exception occured in getLaptop() id: {}", id, exception);
                        throw exception;
                });
        logger.info("Found laptop: {} with id: {}", laptop, id);
        LaptopGetDto laptopGetDto = laptopIMapper.laptopToLaptopGetDto(laptop);
        return laptopGetDto;
    }

    public List<LaptopGetDto> getLaptops(){
        logger.debug("In LaptopService getLaptops() method");
        List<Laptop> laptopList = laptopRepository.findAll();
        if(laptopList.isEmpty()){
            DataNotFoundException exception =  new DataNotFoundException("There are no laptops in database");
            logger.error("Exception occured in getLaptops()", exception);
            throw exception;
        }
        else{
            logger.info("Found list of laptops");
            List<LaptopGetDto> laptopGetDtoList = laptopIMapper.listLaptopToListLaptopGetDto(laptopList);
            return laptopGetDtoList;
        }
    }

    public LaptopGetDto addLaptop(LaptopPostDto laptopPostDto){
        logger.debug("In LaptopService addLaptop() method laptop: {}", laptopPostDto);
        Laptop laptop = laptopIMapper.laptopPostDtoToLaptop(laptopPostDto);
        boolean laptopExist = laptopRepository.findLaptopByNameLaptop(laptop.getNameLaptop()).isPresent();
        if(laptopExist){
            DataDuplicateException exception = new DataDuplicateException(String.format("Cannot create laptop with the same laptop name: %s", laptop.getNameLaptop()));
            logger.error("Exception occured in addLaptop() laptop: {}", laptop, exception);
            throw exception;
        }
        else {
            laptop.setPasswordLaptop(passwordEncoder.encode(laptop.getPasswordLaptop()));
            Laptop laptopDB = laptopRepository.save(laptop);
            logger.info("Laptop: {} added successfully", laptopDB);
            LaptopGetDto laptopGetDto = laptopIMapper.laptopToLaptopGetDto(laptopDB);
            return laptopGetDto;
        }
    }

    public void deleteLaptop(Integer idLaptop){
        logger.debug("In LaptopService deleteLaptop() method");
        Laptop laptop = laptopRepository
                .findById(idLaptop)
                .orElseThrow(() -> {
                    DataNotFoundException exception = new DataNotFoundException(String.format("Cannot delete laptop with id: %d because this not exist in database", idLaptop));
                    logger.error("Exception occured in deleteLaptop() id: {} ", idLaptop, exception);
                    throw exception;
        });
        if(employeeRepository.existsByLaptopEmployee(laptop)){
            DataRelationException exception = new DataRelationException(String.format("Delete failed! First you need to detach Employee from this Laptop id: %d", idLaptop));
            logger.error("Exception occured in deleteLaptop() id: {} laptop not detached", idLaptop, exception);
            throw exception;
        }
        else{
            laptopRepository.deleteById(idLaptop);
            logger.info("Laptop id: {} deleted successfully", idLaptop);
        }
    }

    public LaptopGetDto updateLaptop(Integer id, LaptopPostDto laptopPostDto){
        logger.debug("In LaptopService updateLaptop() method id: {} laptop: {}", id, laptopPostDto);
        Laptop laptop = laptopIMapper.laptopPostDtoToLaptop(laptopPostDto);
        Laptop laptopDB = laptopRepository
                .findById(id)
                .orElseThrow(() -> {
                        DataNotFoundException exception = new DataNotFoundException(String.format("Cannot find laptop with id: %d in database", id));
                        logger.error("Error occured in updateLaptop() findById() id: {} laptop: {}", id, laptop, exception);
                        throw exception;
                });

        boolean otherLaptopWithSameName = laptopRepository
                .findLaptopByNameLaptopAndOtherId(laptop.getNameLaptop(), id)
                .isPresent();

        if(!otherLaptopWithSameName){
            laptopPostDto.setPasswordLaptop(passwordEncoder.encode(laptopPostDto.getPasswordLaptop()));
            laptopIMapper.updateLaptopFromLaptopPostDto(laptopPostDto ,laptopDB);
            laptopRepository.save(laptopDB);
            logger.info("Laptop: {} id: {} updated successfully", laptopDB, id);
            LaptopGetDto laptopGetDto = laptopIMapper.laptopToLaptopGetDto(laptopDB);
            return laptopGetDto;
        }
        else{
            DataDuplicateException exception = new DataDuplicateException(String.format("Laptop with name: %s already exist in database!", laptop.getNameLaptop()));
            logger.error("Error occured in updateLaptop() findLaptopByNameLaptopAndOtherId() id: {} laptop: {}", id, laptopDB, exception);
            throw exception;
        }
    }
}
