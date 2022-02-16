package org.matusikl.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.matusikl.model.Employee;
import org.matusikl.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EmployeeController {

    EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @Operation(summary = "Get employee by id", description = "Get simple employee by specified id", tags = "Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Employee with specified id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class))}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content)})
    @GetMapping(path = "/employees/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getEmployee(@PathVariable ("id") Integer id){
        Employee employee = employeeService.getEmployee(id);
        return ResponseEntity
                .ok()
                .body(employee);
    }

    @Operation(summary = "Get employees", description = "Get list of employees", tags = "Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found list of Employees",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class))}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content)})
    @GetMapping(path = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> getEmployees(){
        List<Employee> employeeList = employeeService.getEmployees();
        return ResponseEntity
                .ok()
                .body(employeeList);
    }

    @Operation(summary = "Save employee", description = "Save specified employee", tags = "Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified employee saved",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class))}),
            @ApiResponse(responseCode = "400", description = "Error: validation of attributes",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Error: duplicate - data already exist in database",
                    content = @Content)})
    @PostMapping(path = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee){
        Employee addedEmployee = employeeService.addEmployee(employee);
        return ResponseEntity
                .ok()
                .body(addedEmployee);
    }

    @Operation(summary = "Delete employee by id", description = "Delete employee with specified id", tags = "Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete employee with specified id",
                    content = {@Content(mediaType = "text/plain",
                            schema = @Schema(implementation = Employee.class))}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content)})
    @DeleteMapping(path = "/employees/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> deleteEmployee(@PathVariable ("id") Integer id){
        employeeService.deleteEmployee(id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(String.format("Deleted employee with id: %d",id));
    }

    @Operation(summary = "Update employee", description = "Update employee with specified id", tags = "Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update employee with specified id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Employee.class))}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Error: duplicate - data already exist in database",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Error: validation of attributes",
                    content = @Content)})
    @PutMapping(path = "/employees/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> updateEmployee(@PathVariable ("id") Integer id,
                                                   @Valid @RequestBody Employee employee){
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        return ResponseEntity
                .ok()
                .body(updatedEmployee);
    }
}
