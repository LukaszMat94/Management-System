package org.matusikl.dto.employeedto;

import org.matusikl.dto.roledto.RoleGetDto;
import java.util.List;

public class EmployeeRoleDto {

    private String nameEmployee;

    private String surnameEmployee;

    private List<RoleGetDto> roleEmployeeList;

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

    public List<RoleGetDto> getRoleEmployeeList() {
        return roleEmployeeList;
    }

    public void setRoleEmployeeList(List<RoleGetDto> roleEmployeeList) {
        this.roleEmployeeList = roleEmployeeList;
    }
}
