package org.matusikl.dto.employeedto;

import org.matusikl.dto.laptopdto.LaptopGetDto;

public class EmployeeLaptopDto {

    private String nameEmployee;

    private String surnameEmployee;

    private LaptopGetDto laptopEmployee;

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

    public LaptopGetDto getLaptopEmployee() {
        return laptopEmployee;
    }

    public void setLaptopEmployee(LaptopGetDto laptopEmployee) {
        this.laptopEmployee = laptopEmployee;
    }
}
