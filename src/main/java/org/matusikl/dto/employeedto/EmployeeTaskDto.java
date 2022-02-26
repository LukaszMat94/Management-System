package org.matusikl.dto.employeedto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.matusikl.dto.taskdto.TaskGetDto;
import java.util.List;

public class EmployeeTaskDto {

    private String nameEmployee;

    private String surnameEmployee;

    @JsonIgnoreProperties("employeeList")
    private List<TaskGetDto> taskList;

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

    public List<TaskGetDto> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<TaskGetDto> taskList) {
        this.taskList = taskList;
    }
}
