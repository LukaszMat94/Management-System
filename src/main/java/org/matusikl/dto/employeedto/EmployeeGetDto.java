package org.matusikl.dto.employeedto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.matusikl.dto.accountdto.AccountGetDto;
import org.matusikl.dto.jobdto.JobGetDto;
import org.matusikl.dto.laptopdto.LaptopGetDto;
import org.matusikl.dto.roledto.RoleGetDto;
import org.matusikl.dto.taskdto.TaskGetDto;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

public class EmployeeGetDto {

    private Integer idEmployee;

    private String nameEmployee;

    private String surnameEmployee;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private ZonedDateTime birthdayEmployee;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private ZonedDateTime employmentDateEmployee;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private ZonedDateTime dismissalDateEmployee;

    private BigDecimal salaryEmployee;

    @Pattern(message = "{employee.pid.pattern}", regexp = "^[0-9]{11}")
    private String personalIdentityNumberEmployee;

    private AccountGetDto accountEmployee;

    @Pattern(message = "{employee.email.pattern}", regexp = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,8}))?$")
    private String emailEmployee;

    @JsonManagedReference
    private LaptopGetDto laptopEmployee;

    private List<RoleGetDto> roleEmployeeList;

    private JobGetDto job;

    @JsonIgnoreProperties("employeeList")
    private List<TaskGetDto> taskList;

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getSurnameEmployee() {
        return surnameEmployee;
    }

    public void setSurnameEmployee(String surnameEmployee) {
        this.surnameEmployee = surnameEmployee;
    }

    public ZonedDateTime getBirthdayEmployee() {
        return birthdayEmployee;
    }

    public void setBirthdayEmployee(ZonedDateTime birthdayEmployee) {
        this.birthdayEmployee = birthdayEmployee;
    }

    public ZonedDateTime getEmploymentDateEmployee() {
        return employmentDateEmployee;
    }

    public void setEmploymentDateEmployee(ZonedDateTime employmentDateEmployee) {
        this.employmentDateEmployee = employmentDateEmployee;
    }

    public ZonedDateTime getDismissalDateEmployee() {
        return dismissalDateEmployee;
    }

    public void setDismissalDateEmployee(ZonedDateTime dismissalDateEmployee) {
        this.dismissalDateEmployee = dismissalDateEmployee;
    }

    public BigDecimal getSalaryEmployee() {
        return salaryEmployee;
    }

    public void setSalaryEmployee(BigDecimal salaryEmployee) {
        this.salaryEmployee = salaryEmployee;
    }

    public String getPersonalIdentityNumberEmployee() {
        return personalIdentityNumberEmployee;
    }

    public void setPersonalIdentityNumberEmployee(String personalIdentityNumberEmployee) {
        this.personalIdentityNumberEmployee = personalIdentityNumberEmployee;
    }

    public AccountGetDto getAccountEmployee() {
        return accountEmployee;
    }

    public void setAccountEmployee(AccountGetDto accountEmployee) {
        this.accountEmployee = accountEmployee;
    }

    public String getEmailEmployee() {
        return emailEmployee;
    }

    public void setEmailEmployee(String emailEmployee) {
        this.emailEmployee = emailEmployee;
    }

    public LaptopGetDto getLaptopEmployee() {
        return laptopEmployee;
    }

    public void setLaptopEmployee(LaptopGetDto laptopEmployee) {
        this.laptopEmployee = laptopEmployee;
    }

    public List<RoleGetDto> getRoleEmployeeList() {
        return roleEmployeeList;
    }

    public void setRoleEmployeeList(List<RoleGetDto> roleEmployeeList) {
        this.roleEmployeeList = roleEmployeeList;
    }

    public JobGetDto getJob() {
        return job;
    }

    public void setJob(JobGetDto job) {
        this.job = job;
    }

    public List<TaskGetDto> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<TaskGetDto> taskList) {
        this.taskList = taskList;
    }
}
