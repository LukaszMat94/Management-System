package org.matusikl.controller;

import org.matusikl.model.Laptop;
import org.matusikl.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import javax.validation.Valid;
import java.util.List;

@RestController
public class LaptopController {

    LaptopService laptopService;

    @Autowired
    public LaptopController(LaptopService laptopService){
        this.laptopService = laptopService;
    }

    @GetMapping(value = "/laptops", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Laptop>> getLaptops(){
        return ResponseEntity
                .ok()
                .body(laptopService.getLaptops());
    }

    @GetMapping(value = "/laptops/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Laptop> getLaptop(@PathVariable("id") Integer id){
        return ResponseEntity
                .ok()
                .body(laptopService.getLaptop(id));
    }

    @PostMapping(value = "/laptops", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Laptop> addLaptop(@Valid @RequestBody Laptop laptop){
        Laptop addedLaptop = laptopService.addLaptop(laptop);
        return ResponseEntity
                .ok()
                .body(addedLaptop);
    }

    @DeleteMapping(value = "/laptops/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> deleteLaptop(@PathVariable ("id") Integer idLaptop){
        laptopService.deleteLaptop(idLaptop);
        return ResponseEntity
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(String.format("Laptop with id: %d deleted", idLaptop));
    }

    @PutMapping(value = "/laptops/{id}", produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Laptop> updateLaptop(@PathVariable ("id") Integer idLaptop,
                                               @Valid @RequestBody Laptop laptop) throws Exception {
        Laptop updatedLaptop = laptopService.updateLaptop(idLaptop, laptop);
        return ResponseEntity
                .ok()
                .body(updatedLaptop);
    }

}
