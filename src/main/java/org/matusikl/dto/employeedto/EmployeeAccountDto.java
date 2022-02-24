package org.matusikl.dto.employeedto;

import org.matusikl.dto.accountdto.AccountGetDto;

public class EmployeeAccountDto {

    private String nameEmployee;

    private String surnameEmployee;

    private AccountGetDto accountEmployee;

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

    public AccountGetDto getAccountEmployee() {
        return accountEmployee;
    }

    public void setAccountEmployee(AccountGetDto accountEmployee) {
        this.accountEmployee = accountEmployee;
    }
}
