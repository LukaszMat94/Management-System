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

    @GetMapping(value = "/get/laptops", produces = "application/json")
    public ResponseEntity<List<Laptop>> getLaptops() {
        return ResponseEntity
                .ok()
                .body(laptopService.getLaptops());
    }

    @GetMapping(value = "/get/laptop/{id}", produces = "application/json")
    public ResponseEntity<Laptop> getLaptop(@PathVariable("id") Integer id){

        return ResponseEntity
                .ok()
                .body(laptopService.getLaptop(id));
    }

    @PostMapping(value = "/add/laptop", consumes = "application/json", produces = {"application/json"})
    public ResponseEntity<?> addLaptop(@Valid @RequestBody Laptop laptop){
        laptopService.addLaptop(laptop);
        return ResponseEntity
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body("Laptop added");
    }

    @DeleteMapping(value = "/delete/laptop/{id}", consumes = "application/json", produces = {"text/plain", "application/json"})
    public ResponseEntity<String> deleteLaptop(@PathVariable ("id") Integer idLaptop){
        laptopService.deleteLaptop(idLaptop);
        return ResponseEntity
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body("Laptop deleted");
    }

    @PutMapping(value = "/update/laptop/{id}", produces = {"text/plain", "application/json"})
    public ResponseEntity<Laptop> updateLaptop(@PathVariable ("id") Integer idLaptop,
                                               @Valid @RequestBody Laptop laptop){
        Laptop updatedLaptop = laptopService.updateLaptop(idLaptop, laptop);
        return ResponseEntity
                .ok()
                .body(updatedLaptop);
    }

}
