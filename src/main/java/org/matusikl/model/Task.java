package org.matusikl.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "MS_Task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTask")
    private Integer idTask;
    @Column(name = "name")
    private String nameTask;
    @Column(name = "description")
    private String descriptionTask;
    @Column(name = "startDate")
    private ZonedDateTime startDateTask;
    @Column(name = "endDate")
    private ZonedDateTime endDateTask;
    @ManyToMany(mappedBy = "taskList")
    private List<Employee> employeeList;

    public Task() {
    }

    public Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public String getDescriptionTask() {
        return descriptionTask;
    }

    public void setDescriptionTask(String descriptionTask) {
        this.descriptionTask = descriptionTask;
    }

    public ZonedDateTime getStartDateTask() {
        return startDateTask;
    }

    public void setStartDateTask(ZonedDateTime startDateTask) {
        this.startDateTask = startDateTask;
    }

    public ZonedDateTime getEndDateTask() {
        return endDateTask;
    }

    public void setEndDateTask(ZonedDateTime endDateTask) {
        this.endDateTask = endDateTask;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
