package org.matusikl.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(name = "MS_Task")
@NamedEntityGraphs({
        @NamedEntityGraph(
            name = "Task.employee",
            attributeNodes = @NamedAttributeNode(value = "employeeList", subgraph = "employeeAtributes"),
            subgraphs = @NamedSubgraph(name = "employeeAtributes",
                attributeNodes = {
                    @NamedAttributeNode(value = "accountEmployee"),
                    @NamedAttributeNode(value = "job"),
                    @NamedAttributeNode(value = "laptopEmployee")
                })),
        @NamedEntityGraph(
            name = "Task.employee.role",
            attributeNodes = @NamedAttributeNode(value = "employeeList", subgraph = "employeeAtributes"),
            subgraphs = @NamedSubgraph(name = "employeeAtributes",
                attributeNodes = {
                    @NamedAttributeNode(value = "accountEmployee"),
                    @NamedAttributeNode(value = "job"),
                    @NamedAttributeNode(value = "laptopEmployee"),
                    @NamedAttributeNode(value = "roleEmployeeList")
                }))
        })
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTask")
    private Integer idTask;

    @Column(name = "name")
    private String nameTask;

    @Column(name = "description")
    private String descriptionTask;

    @Column(name = "startDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private ZonedDateTime startDateTask;

    @Column(name = "endDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private ZonedDateTime endDateTask;

    @ManyToMany(mappedBy = "taskList")
    @JsonIgnoreProperties("taskList")
    private Set<Employee> employeeList;

    public Task() {
    }
    //region Getters-Setters
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

    public Set<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(Set<Employee> employeeList) {
        this.employeeList = employeeList;
    }
    //endregion
}
