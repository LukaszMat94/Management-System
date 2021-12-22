package org.matusikl.service;

import org.matusikl.exception.DataDuplicateException;
import org.matusikl.exception.DataNotFoundException;
import org.matusikl.model.Employee;
import org.matusikl.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class EmployeeService {

    EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployee(Integer id){
        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Get employee failed! There is no employee with id: " + id + " in database"));
        return employee;
    }

    public List<Employee> getEmployees(){
        List<Employee> employeeList = employeeRepository.findAll();
        if(employeeList.isEmpty()){
            throw new DataNotFoundException("Get employees failed! There are no employees in database");
        }
        else{
            return employeeList;
        }
    }

    @Transactional
    public Employee addEmployee(Employee employee){
        boolean existEmployee = employeeRepository
                .findByPersonalIdentityNumberEmployee(employee.getPersonalIdentityNumberEmployee())
                .isPresent();
        if(existEmployee){
            throw new DataDuplicateException("Add employee failed! Employee with given personal identify number already exist");
        }
        else{
        employeeRepository.save(employee);
        return employee;
        }
    }

    @Transactional
    public void deleteEmployee(Integer id){
        boolean existEmployee = employeeRepository.existsById(id);
        if(existEmployee){
            employeeRepository.deleteById(id);
        }
        else{
            throw new DataNotFoundException("Delete employee failed! There is no employee with id: " + id + " in database");
        }
    }

    @Transactional
    public Employee updateEmployee(Integer id, Employee employee){
        Employee employeeDB = employeeRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Update employee failed! There is no employee with id: " + id + " in database"));
        boolean otherEmployeeWithSameIdentifyNumber = employeeRepository
                .findByPersonalIdentityNumberEmployeWithOtherID(employee.getPersonalIdentityNumberEmployee(), id)
                .isPresent();
        if(otherEmployeeWithSameIdentifyNumber){
            throw new DataDuplicateException("Update employee failed! There is already employee with given personal id number");
        }
        else{
            employeeDB.setNameEmployee(employee.getNameEmployee());
            employeeDB.setSurnameEmployee(employee.getSurnameEmployee());
            employeeDB.setEmailEmployee(employee.getEmailEmployee());
            employeeDB.setSalaryEmployee(employee.getSalaryEmployee());
            employeeDB.setBirthdayEmployee(employee.getBirthdayEmployee());
            employeeDB.setDismissalDateEmployee(employee.getDismissalDateEmployee());
            employeeDB.setEmploymentDateEmployee(employee.getEmploymentDateEmployee());
            employeeDB.setJob(employee.getJob());
            employeeDB.setRoleEmployeeList(employee.getRoleEmployeeList());
            employeeDB.setLaptopEmployee(employee.getLaptopEmployee());
            employeeDB.setTaskList(employee.getTaskList());
            employeeDB.setPersonalIdentityNumberEmployee(employee.getPersonalIdentityNumberEmployee());
            employeeDB.setAccountEmployee(employee.getAccountEmployee());
            employeeRepository.save(employeeDB);
            return employeeDB;
        }
    }

}
