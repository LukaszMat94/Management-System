package org.matusikl.controller;

import org.matusikl.exception.DataNotFoundException;
import org.matusikl.model.Laptop;
import org.matusikl.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class LaptopController {

    LaptopService laptopService;

    @Autowired
    public LaptopController(LaptopService laptopService){
        this.laptopService = laptopService;
    }

    @GetMapping(value = "/laptops", produces = "application/json")
    public ResponseEntity<List<Laptop>> getLaptops() {
        return new ResponseEntity<>(laptopService.getLaptops(), HttpStatus.OK);
    }

    @GetMapping(value = "/laptop/{id}", produces = "application/json")
    public ResponseEntity<Laptop> getLaptop(@PathVariable("id") Integer id){

        return new ResponseEntity<>(laptopService.getLaptop(id), HttpStatus.OK);
    }

    @PostMapping(value = "/add/laptop", consumes = "application/json", produces = "text/plain")
    public ResponseEntity<String> addLaptop(@RequestBody Laptop laptop){
        laptopService.addLaptop(laptop);
        return new ResponseEntity<>("Laptop added ", HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/laptop/{id}", consumes = "application/json", produces = "text/plain")
    public ResponseEntity<String> deleteLaptop(@PathVariable ("id") Integer idLaptop){
        laptopService.deleteLaptop(idLaptop);
        return new ResponseEntity<>("Laptop deleted ", HttpStatus.OK);
    }

    @PutMapping(value = "/update/laptop/{id}")
    public ResponseEntity<Laptop> updateLaptop(@PathVariable ("id") Integer idLaptop,
                                               @Valid @RequestBody Laptop laptop){
        laptopService.updateLaptop(idLaptop, laptop);
        return new ResponseEntity<>(laptop, HttpStatus.OK);
    }

}
