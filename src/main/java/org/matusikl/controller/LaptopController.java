package org.matusikl.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get laptop", description = "Get laptop with specified id", tags = "Laptop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found laptop with specified id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Laptop.class))}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content)})
    @GetMapping(value = "/laptops/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Laptop> getLaptop(@PathVariable("id") Integer id){
        return ResponseEntity
                .ok()
                .body(laptopService.getLaptop(id));
    }

    @Operation(summary = "Get laptops", description = "Get list of laptops", tags = "Laptop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found list of laptops",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Laptop.class))}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content)})
    @GetMapping(value = "/laptops", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Laptop>> getLaptops(){
        return ResponseEntity
                .ok()
                .body(laptopService.getLaptops());
    }

    @Operation(summary = "Save laptop", description = "Save specified laptop", tags = "Laptop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Saved specified laptop",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Laptop.class))}),
            @ApiResponse(responseCode = "400", description = "Error: validation of attributes",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Error: duplicate - data already exist in database",
                    content = @Content)})
    @PostMapping(value = "/laptops", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Laptop> addLaptop(@Valid @RequestBody Laptop laptop){
        Laptop addedLaptop = laptopService.addLaptop(laptop);
        return ResponseEntity
                .ok()
                .body(addedLaptop);
    }

    @Operation(summary = "Delete laptop", description = "Delete laptop with specified id", tags = "Laptop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted laptop with specified id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Laptop.class))}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content)})
    @DeleteMapping(value = "/laptops/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> deleteLaptop(@PathVariable ("id") Integer idLaptop){
        laptopService.deleteLaptop(idLaptop);
        return ResponseEntity
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(String.format("Laptop with id: %d deleted", idLaptop));
    }

    @Operation(summary = "Update laptop", description = "Update laptop with specified id", tags = "Laptop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update laptop with specified id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Laptop.class))}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Error: duplicate - data already exist in database",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Error: validation of attributes",
                    content = @Content)})
    @PutMapping(value = "/laptops/{id}", produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Laptop> updateLaptop(@PathVariable ("id") Integer idLaptop,
                                               @Valid @RequestBody Laptop laptop) throws Exception {
        Laptop updatedLaptop = laptopService.updateLaptop(idLaptop, laptop);
        return ResponseEntity
                .ok()
                .body(updatedLaptop);
    }

}
