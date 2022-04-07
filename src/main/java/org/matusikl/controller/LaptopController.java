package org.matusikl.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.matusikl.dto.laptopdto.LaptopGetDto;
import org.matusikl.dto.laptopdto.LaptopPostDto;
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
@SecurityRequirement(name = "bearerAuth")
public class LaptopController {

    private final LaptopService laptopService;
    private final Logger logger = LogManager.getLogger(LaptopController.class);

    @Autowired
    public LaptopController(LaptopService laptopService){
        this.laptopService = laptopService;
    }

    @Operation(summary = "Get laptop", description = "Get laptop with specified id", tags = "Laptop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found laptop with specified id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LaptopGetDto.class))}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content)})
    @GetMapping(value = "/laptops/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LaptopGetDto> getLaptop(@PathVariable("id") Integer id){
        logger.debug("In LaptopController getLaptop() id: {}", id);
        LaptopGetDto laptop = laptopService.getLaptop(id);
        logger.info("Got laptop: {} id: {} from service {}", laptop, id);
        return ResponseEntity
                .ok()
                .body(laptop);
    }

    @Operation(summary = "Get laptops", description = "Get list of laptops", tags = "Laptop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found list of laptops",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LaptopGetDto.class))}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content)})
    @GetMapping(value = "/laptops", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LaptopGetDto>> getLaptops(){
        logger.debug("In LaptopController getLaptops()");
        List<LaptopGetDto> listLaptops = laptopService.getLaptops();
        logger.info("Got list of laptops from service");
        return ResponseEntity
                .ok()
                .body(listLaptops);
    }

    @Operation(summary = "Save laptop", description = "Save specified laptop", tags = "Laptop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Saved specified laptop",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LaptopGetDto.class))}),
            @ApiResponse(responseCode = "400", description = "Error: validation of attributes",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Error: duplicate - data already exist in database",
                    content = @Content)})
    @PostMapping(value = "/laptops", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LaptopGetDto> addLaptop(@Valid @RequestBody LaptopPostDto laptop){
        logger.debug("In LaptopController addLaptop() laptop: {}", laptop);
        LaptopGetDto addedLaptop = laptopService.addLaptop(laptop);
        logger.info("Added laptop: {} from service", addedLaptop);
        return ResponseEntity
                .ok()
                .body(addedLaptop);
    }

    @Operation(summary = "Delete laptop", description = "Delete laptop with specified id", tags = "Laptop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted laptop with specified id",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Error: laptop not detached from employee",
                    content = @Content)})
    @DeleteMapping(value = "/laptops/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> deleteLaptop(@PathVariable ("id") Integer idLaptop){
        logger.debug("In LaptopController deleteLaptop() id: {}", idLaptop);
        laptopService.deleteLaptop(idLaptop);
        logger.info("Deleted laptop id: {} from service", idLaptop);
        return ResponseEntity
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(String.format("Laptop with id: %d deleted", idLaptop));
    }

    @Operation(summary = "Update laptop", description = "Update laptop with specified id", tags = "Laptop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update laptop with specified id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LaptopGetDto.class))}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Error: duplicate - data already exist in database",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Error: validation of attributes",
                    content = @Content)})
    @PutMapping(value = "/laptops/{id}", produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LaptopGetDto> updateLaptop(@PathVariable ("id") Integer idLaptop,
                                               @Valid @RequestBody LaptopPostDto laptop){
        logger.debug("In LaptopController updateLaptop() id: {} laptop: {}", idLaptop, laptop);
        LaptopGetDto updatedLaptop = laptopService.updateLaptop(idLaptop, laptop);
        logger.info("Updated laptop: {} id: {} from service", updatedLaptop, idLaptop);
        return ResponseEntity
                .ok()
                .body(updatedLaptop);
    }
}
