package org.matusikl.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "MS_Employee")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NamedEntityGraph(
        name = "Employee.task",
        attributeNodes = {
                @NamedAttributeNode("taskList")
        })
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEmployee")
    private Integer idEmployee;

    @Column(name = "name")
    private String nameEmployee;

    @Column(name = "surname")
    private String surnameEmployee;

    @Column(name = "birthday")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private ZonedDateTime birthdayEmployee;

    @Column(name = "employmentDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private ZonedDateTime employmentDateEmployee;

    @Column(name = "dismissalDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private ZonedDateTime dismissalDateEmployee;

    @Column(name = "salary")
    private BigDecimal salaryEmployee;

    @Column(name = "personalIdentityNumber")
    private String personalIdentityNumberEmployee;

    @OneToOne
    @JoinColumn(name = "idAccount")
    private Account accountEmployee;

    @Column(name = "email")
    private String emailEmployee;

    @OneToOne
    @JoinColumn(name = "idLaptop")
    @JsonManagedReference
    private Laptop laptopEmployee;

    @ManyToMany
    @JoinTable(name = "MS_Employee_Role",
            joinColumns = @JoinColumn(name = "id_emp", referencedColumnName = "idEmployee"),
            inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id"))
    @Fetch(FetchMode.SUBSELECT)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Role> roleEmployeeList;

    @ManyToOne
    @JoinColumn(name = "idJob")
    private Job job;

    @ManyToMany
    @JoinTable(name = "MS_Employee_Task",
            joinColumns = @JoinColumn(name = "id_emp", referencedColumnName = "idEmployee"),
            inverseJoinColumns = @JoinColumn(name = "id_task", referencedColumnName = "idTask"))
    @JsonIgnoreProperties("employeeList")
    private List<Task> taskList;

    public Employee() {
    }

//region Getters-Settters
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

    public Account getAccountEmployee() {
        return accountEmployee;
    }

    public void setAccountEmployee(Account accountEmployee) {
        this.accountEmployee = accountEmployee;
    }

    public String getEmailEmployee() {
        return emailEmployee;
    }

    public void setEmailEmployee(String emailEmployee) {
        this.emailEmployee = emailEmployee;
    }

    public Laptop getLaptopEmployee() {
        return laptopEmployee;
    }

    public void setLaptopEmployee(Laptop laptopEmployee) {
        this.laptopEmployee = laptopEmployee;
    }

    public List<Role> getRoleEmployeeList() {
        return roleEmployeeList;
    }

    public void setRoleEmployeeList(List<Role> roleEmployeeList) {
        this.roleEmployeeList = roleEmployeeList;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    //endregion

//region ToString
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("idEmployee=").append(idEmployee);
        sb.append(", nameEmployee='").append(nameEmployee).append('\'');
        sb.append(", surnameEmployee='").append(surnameEmployee).append('\'');
        sb.append(", birthdayEmployee=").append(birthdayEmployee);
        sb.append(", employmentDateEmployee=").append(employmentDateEmployee);
        sb.append(", dismissalDateEmployee=").append(dismissalDateEmployee);
        sb.append(", salaryEmployee=").append(salaryEmployee);
        sb.append(", personalIdentityNumberEmployee=").append(personalIdentityNumberEmployee);
        sb.append(", accountEmployee=").append(accountEmployee);
        sb.append(", emailEmployee='").append(emailEmployee).append('\'');
        sb.append('}');
        return sb.toString();
    }
    //endregion
}
