package org.matusikl.service;

import org.matusikl.exception.DataDuplicateException;
import org.matusikl.exception.DataNotFoundException;
import org.matusikl.model.Employee;
import org.matusikl.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class EmployeeService {

    EmployeeRepository employeeRepository;
    private Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployee(Integer id){
        logger.debug("In EmployeeService getEmployee() method");
        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(() -> {
                        DataNotFoundException exception = new DataNotFoundException(String.format("Get employee failed! There is no employee with id: %d in database", id));
                        logger.error("Exception occured in getEmployee() id: {}", id, exception);
                        throw exception;
                });
        logger.info("Found employee with id {}", id);
        return employee;
    }

    public List<Employee> getEmployees(){
        logger.debug("In EmployeeService getEmployees() method");
        List<Employee> employeeList = employeeRepository.findAll();
        if(employeeList.isEmpty()){
            DataNotFoundException exception =  new DataNotFoundException("Get employees failed! There are no employees in database");
            logger.error("Exception occured in getEmployees()", exception);
            throw exception;
        }
        else{
            logger.info("Found list of employees");
            return employeeList;
        }
    }

    @Transactional
    public Employee addEmployee(Employee employee){
        logger.debug("In EmployeeService addEmployee() method");
        boolean existEmployeeByPID = employeeRepository
                .findByPersonalIdentityNumberEmployee(employee.getPersonalIdentityNumberEmployee())
                .isPresent();
        boolean existEmployeeByEmail = employeeRepository
                .findByEmailEmployee(employee.getEmailEmployee())
                .isPresent();
        if(existEmployeeByPID){
            DataDuplicateException exception = new DataDuplicateException(String.format("Add employee failed! Employee with given personal identify number already exist"));
            logger.error("Exception occured in addEmployee() findByPersonalIdentityNumberEmployee(): {}", exception);
            throw exception;
        }
        else if(existEmployeeByEmail){
            DataDuplicateException exception = new DataDuplicateException(String.format("Add employee failed! Employee with given email already exist"));
            logger.error("Exception occured in addEmployee() findByEmailEmployee(): {}", exception);
            throw exception;
        }
        else{
            Employee employeeDB = employeeRepository.save(employee);
            logger.info("Employee {} added successfully", employeeDB);
            return employeeDB;
        }
    }

    @Transactional
    public void deleteEmployee(Integer id){
        logger.debug("In EmployeeService deleteEmployee() method");
        if(employeeRepository.existsById(id)){
            employeeRepository.deleteById(id);
            logger.info("Employee id {} deleted successfully", id);
        }
        else{
            DataNotFoundException exception = new DataNotFoundException(String.format("Delete employee failed! There is no employee with id: %d in database", id));
            logger.error("Exception occured in deleteEmployee() id: {} ", id, exception);
            throw exception;
        }
    }

    @Transactional
    public Employee updateEmployee(Integer id, Employee employee){
        logger.debug("In EmployeeService updateEmployee() method");
        Employee employeeDB = employeeRepository
                .findById(id)
                .orElseThrow(() -> {
                        DataNotFoundException exception = new DataNotFoundException(String.format("Update employee failed! There is no employee with id: %d in database", id));
                        logger.error("Error occured in updateEmployee() findById()", exception);
                        throw exception;
                });

        boolean otherEmployeeWithSameIdentifyNumber = employeeRepository
                .findByPersonalIdentityNumberEmployeeWithOtherID(employee.getPersonalIdentityNumberEmployee(), id)
                .isPresent();

        boolean otherEmployeeWithSameEmail = employeeRepository
                .findByEmailWithOtherID(employee.getEmailEmployee(), id)
                .isPresent();

        if(otherEmployeeWithSameIdentifyNumber){
            DataDuplicateException exception = new DataDuplicateException(String.format("Update employee failed! There is already employee with given personal id number"));
            logger.error("Error occured in updateEmployee() findByPersonalIdentityNumberEmployeeWithOtherID()", exception);
            throw exception;
        }
        else if(otherEmployeeWithSameEmail){
            DataDuplicateException exception = new DataDuplicateException(String.format("Update employee failed! There is already employee with given email"));
            logger.error("Error occured in updateEmployee() findByEmailWithOtherID()", exception);
            throw exception;
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
            logger.info("Employee id {} updated successfully", id);
            return employeeDB;
        }
    }
}
