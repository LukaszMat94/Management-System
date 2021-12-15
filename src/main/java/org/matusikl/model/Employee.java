package org.matusikl.model;

import lombok.Data;

import java.sql.Date;

@Data
public class Employee {

    private Integer idEmployee;
    private String nameEmployee;
    private String surnameEmployee;
    private Date birthdayEmployee;
    private Date employmentDateEmployee;
    private Date dismissalDateEmployee;
    private int salaryEmployee; //Wynagrodzenie w intach? Do piniędzy tylko BigDecimal
    private String personalIdentityNumberEmployee;
    private Account accountEmployee;
    private String emailEmployee;
    private Laptop laptopEmployee;
}
