package org.matusikl.model;

import java.sql.Date;

public class Employee {

    private Integer idEmployee;
    private String nameEmployee;
    private String surnameEmployee;
    private Date birthdayEmployee;
    private Date employmentDateEmployee;
    private Date dismissalDateEmployee;
    private int salaryEmployee;
    private String personalIdentityNumberEmployee;
    private Account accountEmployee;
    private String emailEmployee;
    private Laptop laptopEmployee;

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

    public Date getBirthdayEmployee() {
        return birthdayEmployee;
    }

    public void setBirthdayEmployee(Date birthdayEmployee) {
        this.birthdayEmployee = birthdayEmployee;
    }

    public Date getEmploymentDateEmployee() {
        return employmentDateEmployee;
    }

    public void setEmploymentDateEmployee(Date employmentDateEmployee) {
        this.employmentDateEmployee = employmentDateEmployee;
    }

    public Date getDismissalDateEmployee() {
        return dismissalDateEmployee;
    }

    public void setDismissalDateEmployee(Date dismissalDateEmployee) {
        this.dismissalDateEmployee = dismissalDateEmployee;
    }

    public int getSalaryEmployee() {
        return salaryEmployee;
    }

    public void setSalaryEmployee(int salaryEmployee) {
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
        sb.append(", laptopEmployee=").append(laptopEmployee);
        sb.append('}');
        return sb.toString();
    }
    //endregion
}
