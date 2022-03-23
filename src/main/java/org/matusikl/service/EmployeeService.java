package org.matusikl.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.matusikl.dto.employeedto.EmployeeAccountDto;
import org.matusikl.dto.employeedto.EmployeeGetDto;
import org.matusikl.dto.employeedto.EmployeeJobDto;
import org.matusikl.dto.employeedto.EmployeeLaptopDto;
import org.matusikl.dto.employeedto.EmployeePostDto;
import org.matusikl.dto.employeedto.EmployeeRoleDto;
import org.matusikl.dto.employeedto.EmployeeTaskDto;
import org.matusikl.exception.DataDuplicateException;
import org.matusikl.exception.DataNotFoundException;
import org.matusikl.model.Account;
import org.matusikl.model.Employee;
import org.matusikl.mapperinterface.EmployeeIMapper;
import org.matusikl.model.Job;
import org.matusikl.model.Laptop;
import org.matusikl.model.Role;
import org.matusikl.model.Task;
import org.matusikl.repository.AccountRepository;
import org.matusikl.repository.EmployeeRepository;
import org.matusikl.repository.JobRepository;
import org.matusikl.repository.LaptopRepository;
import org.matusikl.repository.RoleRepository;
import org.matusikl.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AccountRepository accountRepository;
    private final LaptopRepository laptopRepository;
    private final JobRepository jobRepository;
    private final TaskRepository taskRepository;
    private final RoleRepository roleRepository;
    private final EmployeeIMapper employeeIMapper;
    private final Logger logger = LogManager.getLogger(EmployeeService.class);

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository,
                           AccountRepository accountRepository,
                           LaptopRepository laptopRepository,
                           JobRepository jobRepository,
                           TaskRepository taskRepository,
                           RoleRepository roleRepository,
                           EmployeeIMapper employeeIMapper){
        this.employeeRepository = employeeRepository;
        this.accountRepository = accountRepository;
        this.laptopRepository = laptopRepository;
        this.jobRepository = jobRepository;
        this.taskRepository = taskRepository;
        this.roleRepository = roleRepository;
        this.employeeIMapper = employeeIMapper;
    }

    public EmployeeGetDto getEmployee(Integer id){
        logger.debug("In EmployeeService getEmployee() method id: {}", id);
        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(() -> {
                        DataNotFoundException exception = new DataNotFoundException(String.format("Get employee failed! There is no employee with id: %d in database", id));
                        logger.error("Exception occured in getEmployee() id: {}", id, exception);
                        throw exception;
                });
        logger.info("Found employee: {} with id {}", employee, id);
        EmployeeGetDto employeeDto = employeeIMapper.employeeToEmployeeGetDto(employee);
        return employeeDto;
    }

    public List<EmployeeGetDto> getEmployees(){
        logger.debug("In EmployeeService getEmployees() method");
        List<Employee> employeeList = employeeRepository.findAll();
        if(employeeList.isEmpty()){
            DataNotFoundException exception =  new DataNotFoundException("Get employees failed! There are no employees in database");
            logger.error("Exception occured in getEmployees()", exception);
            throw exception;
        }
        else{
            logger.info("Found list of employees");
            List<EmployeeGetDto> employeeGetDtoList = employeeIMapper.listEmployeeToListEmployeeGetDto(employeeList);
            return employeeGetDtoList;
        }
    }

    @Transactional
    public EmployeePostDto addEmployee(EmployeePostDto employeePostDto){
        logger.debug("In EmployeeService addEmployee() method employee: {}", employeePostDto);
        Employee employee = employeeIMapper.employeePostDtoToEmployee(employeePostDto);
        boolean existEmployeeByPID = employeeRepository
                .existsByPersonalIdentityNumberEmployee(employee.getPersonalIdentityNumberEmployee());

        boolean existEmployeeByEmail = employeeRepository
                .existsByEmailEmployee(employee.getEmailEmployee());

        if(existEmployeeByPID){
            DataDuplicateException exception = new DataDuplicateException(String.format("Add employee failed! Employee with given personal identify number already exist"));
            logger.error("Exception occured in addEmployee() findByPersonalIdentityNumberEmployee() employee: {}", employee, exception);
            throw exception;
        }
        else if(existEmployeeByEmail){
            DataDuplicateException exception = new DataDuplicateException(String.format("Add employee failed! Employee with given email already exist"));
            logger.error("Exception occured in addEmployee() findByEmailEmployee() employee: {}", employee, exception);
            throw exception;
        }
        else{
            Employee employeeDB = employeeRepository.save(employee);
            logger.info("Employee: {} added successfully", employeeDB);
            employeePostDto = employeeIMapper.employeeToEmployeePostDto(employeeDB);
            return employeePostDto;
        }
    }

    @Transactional
    public void deleteEmployee(Integer id){
        logger.debug("In EmployeeService deleteEmployee() method id: {}", id);
        if(employeeRepository.existsById(id)){
            employeeRepository.deleteById(id);
            logger.info("Employee id: {} deleted successfully", id);
        }
        else{
            DataNotFoundException exception = new DataNotFoundException(String.format("Delete employee failed! There is no employee with id: %d in database", id));
            logger.error("Exception occured in deleteEmployee() id: {} ", id, exception);
            throw exception;
        }
    }

    @Transactional
    public EmployeeGetDto updateEmployee(Integer id, EmployeePostDto employeePostDto){
        logger.debug("In EmployeeService updateEmployee() method id: {} employee: {}", id, employeePostDto);
        Employee employee = employeeIMapper.employeePostDtoToEmployee(employeePostDto);
        Employee employeeDB = employeeRepository
                .findById(id)
                .orElseThrow(() -> {
                        DataNotFoundException exception = new DataNotFoundException(String.format("Update employee failed! There is no employee with id: %d in database", id));
                        logger.error("Error occured in updateEmployee() findById() id: {} employee: {}", id, employee, exception);
                        throw exception;
                });

        boolean otherEmployeeWithSameIdentifyNumber = employeeRepository
                .existsByPersonalIdentityNumberEmployeeAndIdEmployeeNot(employee.getPersonalIdentityNumberEmployee(), id);

        boolean otherEmployeeWithSameEmail = employeeRepository
                .existsByEmailEmployeeAndIdEmployeeNot(employee.getEmailEmployee(), id);

        if(otherEmployeeWithSameIdentifyNumber){
            DataDuplicateException exception = new DataDuplicateException(String.format("Update employee failed! There is already employee with given personal id number"));
            logger.error("Error occured in updateEmployee() findByPersonalIdentityNumberEmployeeWithOtherID() id: {} employee: {}", id, employeeDB, exception);
            throw exception;
        }
        else if(otherEmployeeWithSameEmail){
            DataDuplicateException exception = new DataDuplicateException(String.format("Update employee failed! There is already employee with given email"));
            logger.error("Error occured in updateEmployee() findByEmailWithOtherID() id: {} employee: {}", id, employeeDB, exception);
            throw exception;
        }
        else{
            employeeIMapper.updateEmployeeFromEmployeePostDto(employeePostDto, employeeDB);
            employeeRepository.save(employeeDB);
            logger.info("Employee: {} id: {} updated successfully", employeeDB, id);
            EmployeeGetDto employeeGetDto = employeeIMapper.employeeToEmployeeGetDto(employeeDB);
            return employeeGetDto;
        }
    }

    @Transactional
    public EmployeeAccountDto assignAccountToEmployee(Integer idEmp, Integer idAcc){
        logger.debug("In EmployeeService assignAccountToEmployee() method idEmp: {} idAcc: {}", idEmp, idAcc);
        Employee employee = employeeRepository
                .findById(idEmp)
                .orElseThrow(() -> {
                        DataNotFoundException exception = new DataNotFoundException(String.format("Assign failed! There is no employee in database with id: %s",idEmp));
                        logger.error("Error occured in EmployeeService assignAccountToEmployee() idEmp: {} idAcc: {}", idEmp, idAcc, exception);
                        throw exception;
                });
        Account account = accountRepository
                .findById(idAcc)
                .orElseThrow(() -> {
                        DataNotFoundException exception = new DataNotFoundException(String.format("Assign failed! There is no account in database with id: %s", idAcc));
                        logger.error("Error occured in EmployeeService assignAccountToEmployee() idEmp: {} idAcc: {}", idEmp, idAcc, exception);
                        throw exception;
        });

        boolean accountAssignedToOtherEmployee = employeeRepository
                .existsByAccountEmployee(account);

        if(accountAssignedToOtherEmployee){
            DataDuplicateException exception = new DataDuplicateException("Assign failed! This account is already assigned to other employee in database");
            logger.error("Error occured in EmployeeController assignAccountToEmployee(): Account: {} idAcc: {} already assigned to other employee!", account, idAcc, exception);
            throw exception;
        }
        else{
            employee.setAccountEmployee(account);
            employeeRepository.save(employee);
            logger.info("Account: {} idAcc: {} saved to Employee: {} idEmp: {} successfully", account, idAcc, employee, idEmp);
            EmployeeAccountDto employeeAccountDto = employeeIMapper.employeeToEmployeeAccountDto(employee);
            return employeeAccountDto;
        }
    }

    @Transactional
    public EmployeeLaptopDto assignLaptopToEmployee(Integer idEmp, Integer idLap){
        logger.debug("In EmployeeService assignLaptopToEmployee() method idEmp: {} idLap: {}", idEmp, idLap);
        Employee employee = employeeRepository
                .findById(idEmp)
                .orElseThrow(() -> {
                    DataNotFoundException exception = new DataNotFoundException(String.format("Assign failed! There is no employee in database with id: %s",idEmp));
                    logger.error("Error occured in EmployeeService assignLaptopToEmployee() idEmp: {}, idLap: {}", idEmp, idLap, exception);
                    throw exception;
                });
        Laptop laptop = laptopRepository
                .findById(idLap)
                .orElseThrow(() -> {
                    DataNotFoundException exception = new DataNotFoundException(String.format("Assign failed! There is no laptop in database with id: %s", idLap));
                    logger.error("Error occured in EmployeeService assignLaptopToEmployee() idEmp: {} idLap: {}", idEmp, idLap, exception);
                    throw exception;
                });

        boolean laptopAssignedToOtherEmployee = employeeRepository
                .existsByLaptopEmployee(laptop);

        if(laptopAssignedToOtherEmployee){
            DataDuplicateException exception = new DataDuplicateException("Assign failed! This laptop is already assigned to other employee in database");
            logger.error("Error occured in EmployeeController assignLaptopToEmployee(): Laptop: {} idLap: {} already assigned to other employee!", laptop, idLap, exception);
            throw exception;
        }
        else{
            employee.setLaptopEmployee(laptop);
            employeeRepository.save(employee);
            logger.info("Laptop: {} idLap: {} saved to Employee: {} idEmp: {} successfully", laptop, idLap, employee, idEmp);
            EmployeeLaptopDto employeeLaptopDto = employeeIMapper.employeeToEmployeeLaptopDto(employee);
            return employeeLaptopDto;
        }
    }

    @Transactional
    public EmployeeJobDto assignJobToEmployee(Integer idEmp, Integer idJob){
        logger.debug("In EmployeeService assignJobToEmployee() method idEmp: {} idJob: {}", idEmp, idJob);
        Employee employee = employeeRepository
                .findById(idEmp)
                .orElseThrow(() -> {
                    DataNotFoundException exception = new DataNotFoundException(String.format("Assign failed! There is no employee in database with id: %s",idEmp));
                    logger.error("Error occured in EmployeeService assignJobToEmployee() idEmp: {} idJob: {}", idEmp, idJob, exception);
                    throw exception;
                });
        Job job = jobRepository
                .findById(idJob)
                .orElseThrow(() -> {
                    DataNotFoundException exception = new DataNotFoundException(String.format("Assign failed! There is no job in database with id: %s", idJob));
                    logger.error("Error occured in EmployeeService assignJobToEmployee() idEmp: {} idJob: {}", idEmp, idJob, exception);
                    throw exception;
                });

        employee.setJob(job);
        employeeRepository.save(employee);
        logger.info("Job: {} idJob: {} saved to Employee: {} idEmp: {} successfully", job, idJob, employee, idEmp);
        EmployeeJobDto employeeJobDto = employeeIMapper.employeeToEmployeeJobDto(employee);
        return employeeJobDto;
    }

    @Transactional
    public EmployeeTaskDto assignTaskToEmployee(Integer idTask, Integer idEmp){
        logger.debug("In EmployeeService assignTaskToEmployee() method idTask: {} idEmp: {}", idTask, idEmp);
        Task task = taskRepository
                .findById(idTask)
                .orElseThrow(() -> {
                    DataNotFoundException exception = new DataNotFoundException(String.format("Assign failed! There is no task in database with id: %s", idTask));
                    logger.error("Error occured in EmployeeService assignTaskToEmployee() idTask: {} idEmp: {}", idTask, idEmp, exception);
                    throw exception;
                });
        Employee employee = employeeRepository
                .findById(idEmp)
                .orElseThrow(() -> {
                    DataNotFoundException exception = new DataNotFoundException(String.format("Assign failed! There is no employee in database with id: %s",idEmp));
                    logger.error("Error occured in EmployeeService assignTaskToEmployee() idTask: {} idEmp: {}", idTask, idEmp, exception);
                    throw exception;
                });

        employee.getTaskList().add(task);
        employeeRepository.save(employee);
        logger.info("Task: {} idTask: {} saved to Employee: {} idEmp: {} successfully", task, idTask, employee, idEmp);
        EmployeeTaskDto employeeTaskDto = employeeIMapper.employeeToEmployeeTaskDto(employee);
        return employeeTaskDto;
    }

    @Transactional
    public EmployeeRoleDto assignRoleToEmployee(Integer idRole, Integer idEmp){
        logger.debug("In EmployeeService assignRoleToEmployee() method idEmp: {} idRole: {}", idEmp, idRole);
        Employee employee = employeeRepository
                .findById(idEmp)
                .orElseThrow(() -> {
                    DataNotFoundException exception = new DataNotFoundException(String.format("Assign failed! There is no employee in database with id: %s",idEmp));
                    logger.error("Error occured in EmployeeService assignRoleToEmployee() idEmp: {}, idLap: {}", idEmp, idRole, exception);
                    throw exception;
                });
        Role role = roleRepository
                .findById(idRole)
                .orElseThrow(() -> {
                    DataNotFoundException exception = new DataNotFoundException(String.format("Assign failed! There is no role in database with id: %s", idRole));
                    logger.error("Error occured in EmployeeService assignRoleToEmployee() idEmp: {} idRole: {}", idEmp, idRole, exception);
                    throw exception;
                });

        for(Role roleExist : employee.getRoleEmployeeList()){
            if(roleExist.equals(role)){
                DataDuplicateException exception = new DataDuplicateException("Assign failed! This role is already assigned to employee in database");
                logger.error("Error occured in EmployeeController assignRoleToEmployee(): Role: {} idRole: {} already assigned to employee!", role, idRole, exception);
                throw exception;
            }
        }
        employee.getRoleEmployeeList().add(role);
        employeeRepository.save(employee);
        logger.info("Role: {} idRole: {} saved to Employee: {} idEmp: {} successfully", role, idRole, employee, idEmp);
        EmployeeRoleDto employeeRoleDto = employeeIMapper.employeeToEmployeeRoleDto(employee);
        return employeeRoleDto;
    }
}
