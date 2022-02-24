package org.matusikl.dto.employeedto;

import org.matusikl.dto.jobdto.JobGetDto;

public class EmployeeJobDto {

    private String nameEmployee;

    private String surnameEmployee;

    private JobGetDto job;

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

    public JobGetDto getJob() {
        return job;
    }

    public void setJob(JobGetDto job) {
        this.job = job;
    }
}
