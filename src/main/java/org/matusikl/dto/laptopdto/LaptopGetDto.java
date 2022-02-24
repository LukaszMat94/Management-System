package org.matusikl.dto.laptopdto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.matusikl.model.Employee;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LaptopGetDto {

    private Integer idLaptop;

    @Size(min = 4, max = 20, message = "{laptop.namelaptop.size}")
    private String nameLaptop;

    @NotEmpty(message = "{laptop.brandLaptop.empty}")
    private String brandLaptop;

    @Pattern(message = "{laptop.login.pattern}",
            regexp = "[a-zA-Z0-9]{8,}$")
    private String loginLaptop;

    @JsonBackReference
    private Employee employee;

    public Integer getIdLaptop() {
        return idLaptop;
    }

    public void setIdLaptop(Integer idLaptop) {
        this.idLaptop = idLaptop;
    }

    public String getNameLaptop() {
        return nameLaptop;
    }

    public void setNameLaptop(String nameLaptop) {
        this.nameLaptop = nameLaptop;
    }

    public String getBrandLaptop() {
        return brandLaptop;
    }

    public void setBrandLaptop(String brandLaptop) {
        this.brandLaptop = brandLaptop;
    }

    public String getLoginLaptop() {
        return loginLaptop;
    }

    public void setLoginLaptop(String loginLaptop) {
        this.loginLaptop = loginLaptop;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
