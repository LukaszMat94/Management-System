package org.matusikl.dto.taskdto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.ZonedDateTime;

public class TaskPostDto {

    private String nameTask;

    private String descriptionTask;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private ZonedDateTime startDateTask;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private ZonedDateTime endDateTask;

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
}
