package org.matusikl.controller;

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
import java.util.List;

@RestController
public class EmployeeController {

    EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/get/employee/{id}", produces = "application/json")
    public ResponseEntity<Employee> getEmployee(@PathVariable ("id") Integer id){
        Employee employee = employeeService.getEmployee(id);
        return ResponseEntity
                .ok()
                .body(employee);
    }

    @GetMapping(path = "/get/employees", produces = "application/json")
    public ResponseEntity<List<Employee>> getEmployees(){
        List<Employee> employeeList = employeeService.getEmployees();
        return ResponseEntity
                .ok()
                .body(employeeList);
    }

    @PostMapping(path = "/add/employee", produces = "application/json")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        Employee addedEmployee = employeeService.addEmployee(employee);
        return ResponseEntity
                .ok()
                .body(addedEmployee);
    }

    @DeleteMapping(path = "/delete/employee/{id}", produces = {"application/json", "text/plain"})
    public ResponseEntity<String> deleteEmployee(@PathVariable ("id") Integer id){
        employeeService.deleteEmployee(id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body("Deleted employee with id: " + id);
    }

    @PutMapping(path = "/update/employe/{id}", produces = "application/json")
    public ResponseEntity<Employee> updateEmployee(@PathVariable ("id") Integer id,
                                                   @RequestBody Employee employee){
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        return ResponseEntity
                .ok()
                .body(updatedEmployee);
    }
}
