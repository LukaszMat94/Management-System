package org.matusikl.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.matusikl.dto.employeedto.EmployeeAccountDto;
import org.matusikl.dto.employeedto.EmployeeGetDto;
import org.matusikl.dto.employeedto.EmployeeJobDto;
import org.matusikl.dto.employeedto.EmployeeLaptopDto;
import org.matusikl.dto.employeedto.EmployeePostDto;
import org.matusikl.dto.employeedto.EmployeeRoleDto;
import org.matusikl.dto.employeedto.EmployeeTaskDto;
import org.matusikl.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final Logger logger = LogManager.getLogger(EmployeeController.class);

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @Operation(summary = "Get employee by id", description = "Get simple employee by specified id", tags = "Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Employee with specified id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeGetDto.class))}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content)})
    @GetMapping(path = "/employees/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeGetDto> getEmployee(@PathVariable("id") Integer id){
        logger.debug("In EmployeeController getEmployee() id: {}", id);
        EmployeeGetDto employee = employeeService.getEmployee(id);
        logger.info("Got employee: {} id: {} from service", employee, id);
        return ResponseEntity
                .ok()
                .body(employee);
    }

    @Operation(summary = "Get employees", description = "Get list of employees", tags = "Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found list of Employees",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeGetDto.class))}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content)})
    @GetMapping(path = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeGetDto>> getEmployees(){
        logger.debug("In EmployeeController getEmployees()");
        List<EmployeeGetDto> employeeList = employeeService.getEmployees();
        logger.info("Got list of employees from service");
        return ResponseEntity
                .ok()
                .body(employeeList);
    }

    @Operation(summary = "Save employee", description = "Save specified employee", tags = "Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specified employee saved",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeePostDto.class))}),
            @ApiResponse(responseCode = "400", description = "Error: validation of attributes",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Error: duplicate - data already exist in database",
                    content = @Content)})
    @PostMapping(path = "/employees", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeePostDto> addEmployee(@Valid @RequestBody EmployeePostDto employee){
        logger.debug("In EmployeeController addEmployee() employee: {}", employee);
        EmployeePostDto addedEmployee = employeeService.addEmployee(employee);
        logger.info("Added employee: {} from service", addedEmployee);
        return ResponseEntity
                .ok()
                .body(addedEmployee);
    }

    @Operation(summary = "Delete employee by id", description = "Delete employee with specified id", tags = "Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete employee with specified id",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content)})
    @DeleteMapping(path = "/employees/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> deleteEmployee(@PathVariable ("id") Integer id){
        logger.debug("In EmployeeController deleteEmployee() id: {}", id);
        employeeService.deleteEmployee(id);
        logger.info("Deleted employee id: {} from service", id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(String.format("Deleted employee with id: %d",id));
    }

    @Operation(summary = "Update employee", description = "Update employee with specified id", tags = "Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update employee with specified id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeGetDto.class))}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Error: duplicate - data already exist in database",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Error: validation of attributes",
                    content = @Content)})
    @PutMapping(path = "/employees/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeGetDto> updateEmployee(@PathVariable ("id") Integer id,
                                                   @Valid @RequestBody EmployeePostDto employee){
        logger.debug("In EmployeeController updateEmployee() id: {} employee: {}", id, employee);
        EmployeeGetDto updatedEmployee = employeeService.updateEmployee(id, employee);
        logger.info("Updated employee: {} id: {} from service", updatedEmployee, id);
        return ResponseEntity
                .ok()
                .body(updatedEmployee);
    }

    @Operation(summary = "Assign account to employee", description = "Assign account with specified id to employee with specified id", tags = "Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assigned account id to employee id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeAccountDto.class))}),
            @ApiResponse(responseCode = "404", description = "Error: Account/Employee not found in database",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Error: duplicate - Account already assigned to other employee",
                    content = @Content)
    })
    @PatchMapping(path = "/employees/{idEmp}/accounts/{idAcc}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeAccountDto> assignAccountToEmployee(@PathVariable ("idEmp") Integer idEmp,
                                                                      @PathVariable ("idAcc") Integer idAcc){
        logger.debug("In EmployeeController assignAccountToEmployee() idEmp: {} idAcc: {}", idEmp, idAcc);
        EmployeeAccountDto employee = employeeService.assignAccountToEmployee(idEmp, idAcc);
        logger.info("Updated employee! Assigned Account id: {} to Employee id: {}", idAcc, idEmp);
        return ResponseEntity
                .ok()
                .body(employee);
    }

    @Operation(summary = "Assign laptop to employee", description = "Assign laptop with specified id to employee with specified id", tags = "Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assigned laptop id to employee id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeLaptopDto.class))}),
            @ApiResponse(responseCode = "404", description = "Error: Laptop/Employee not found in database",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Error: duplicate - Laptop already assigned to other employee",
                    content = @Content)
    })
    @PatchMapping(path = "/employees/{idEmp}/laptops/{idLap}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeLaptopDto> assignLaptopToEmployee(@PathVariable ("idEmp") Integer idEmp,
                                                                    @PathVariable ("idLap") Integer idLap){
        logger.debug("In EmployeeController assignLaptopToEmployee() idEmp: {} idLap: {}", idEmp, idLap);
        EmployeeLaptopDto employee = employeeService.assignLaptopToEmployee(idEmp, idLap);
        logger.info("Updated employee! Assigned Laptop id: {} to Employee id: {}", idLap, idEmp);
        return ResponseEntity
                .ok()
                .body(employee);
    }

    @Operation(summary = "Assign job to employee", description = "Assign job with specified id to employee with specified id", tags = "Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assigned job id to employee id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeJobDto.class))}),
            @ApiResponse(responseCode = "404", description = "Error: Job/Employee not found in database",
                    content = @Content)
    })
    @PatchMapping(path = "/employees/{idEmp}/jobs/{idJob}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeJobDto> assignJobToEmployee(@PathVariable ("idEmp") Integer idEmp,
                                                              @PathVariable ("idJob") Integer idJob){
        logger.debug("In EmployeeController assignJobToEmployee() idEmp: {} idJob: {}", idEmp, idJob);
        EmployeeJobDto employee = employeeService.assignJobToEmployee(idEmp, idJob);
        logger.info("Updated employee! Assigned Job id: {} to Employee id: {}", idJob, idEmp);
        return ResponseEntity
                .ok()
                .body(employee);
    }

    @Operation(summary = "Assign task to employee", description = "Assign task with specified id to employee with specified id", tags = "Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assigned task id to employee id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeTaskDto.class))}),
            @ApiResponse(responseCode = "404", description = "Error: Employee/Task not found in database",
                    content = @Content)
    })
    @PatchMapping(path = "/employees/{idEmp}/tasks/{idTask}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeTaskDto> assignTaskToEmployee(@PathVariable ("idEmp") Integer idEmp,
                                                                @PathVariable ("idTask") Integer idTask){
        logger.debug("In EmployeeController assignTaskToEmployee() idTask: {} idEmp: {}", idTask, idEmp);
        EmployeeTaskDto employee = employeeService.assignTaskToEmployee(idTask, idEmp);
        logger.info("Updated employee! Assigned Task id: {} to Employee id: {}", idTask, idEmp);
        return ResponseEntity
                .ok()
                .body(employee);
    }

    @Operation(summary = "Assign role to employee", description = "Assign role with specified id to employee with specified id", tags = "Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assigned role id to employee id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeRoleDto.class))}),
            @ApiResponse(responseCode = "404", description = "Error: Employee/Role not found in database",
                    content = @Content)
    })
    @PatchMapping(path = "/employees/{idEmp}/roles/{idRole}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeRoleDto> assignRoleToEmployee(@PathVariable ("idEmp") Integer idEmp,
                                                                @PathVariable ("idRole") Integer idRole){
        logger.debug("In EmployeeController assignRoleToEmployee() idRole: {} idEmp: {}", idRole, idEmp);
        EmployeeRoleDto employee = employeeService.assignRoleToEmployee(idRole, idEmp);
        logger.info("Updated employee! Assigned Role id: {} to Employee id: {}", idRole, idEmp);
        return ResponseEntity
                .ok()
                .body(employee);
    }
}
