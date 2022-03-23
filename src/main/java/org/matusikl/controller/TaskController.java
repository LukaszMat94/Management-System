package org.matusikl.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.matusikl.dto.taskdto.TaskGetDto;
import org.matusikl.dto.taskdto.TaskPostDto;
import org.matusikl.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;
    private final Logger logger = LogManager.getLogger(TaskController.class);

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @Operation(summary = "Get task", description = "Get task with specified id", tags = "Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found task with specified id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskGetDto.class))}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content)})
    @GetMapping(path = "/tasks/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskGetDto> getTask(@PathVariable("id") Integer id){
        logger.debug("In TaskController getTask() id: {}", id);
        TaskGetDto task = taskService.getTask(id);
        logger.info("Got task: {} id: {} from service", task, id);
        return ResponseEntity
                .ok()
                .body(task);
    }

    @Operation(summary = "Get tasks", description = "Get list of tasks", tags = "Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found list of tasks",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskGetDto.class))}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content)})
    @GetMapping(path = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TaskGetDto>> getTasks(){
        logger.debug("In TaskController getTasks()");
        List<TaskGetDto> taskList = taskService.getTasks();
        logger.info("Got list of tasks from service");
        return ResponseEntity
                .ok()
                .body(taskList);
    }

    @Operation(summary = "Save task", description = "Save specified task", tags = "Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Saved specified task",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskGetDto.class))}),
            @ApiResponse(responseCode = "400", description = "Error: validation of attributes",
                    content = @Content)})
    @PostMapping(path = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskGetDto> addTask(@Valid @RequestBody TaskPostDto task){
        logger.debug("In TaskController addTask() task: {}", task);
        TaskGetDto addedTask = taskService.addTask(task);
        logger.info("Added task: {} from service", addedTask);
        return ResponseEntity
                .ok()
                .body(addedTask);
    }

    @Operation(summary = "Delete task", description = "Delete task with specified id", tags = "Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted task with specified id",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content)})
    @DeleteMapping(path = "/tasks/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> deleteTask(@PathVariable ("id") Integer id){
        logger.debug("In TaskController deleteTask() id: {}", id);
        taskService.deleteTask(id);
        logger.info("Deleted task id: {} from service", id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(String.format("Task with id: %d deleted", id));
    }

    @Operation(summary = "Update task", description = "Update task with specified id", tags = "Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated task with specified id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskGetDto.class))}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Error: validation of attributes",
                    content = @Content)})
    @PutMapping(value = "/tasks/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskGetDto> updateTask(@PathVariable ("id") Integer id,
                                           @Valid @RequestBody TaskPostDto task){
        logger.debug("In TaskController updateTask() id: {} task: {}", id, task);
        TaskGetDto updatedTask = taskService.updateTask(id, task);
        logger.info("Updated task: {} id: {} from service", updatedTask, id);
        return ResponseEntity
                .ok()
                .body(updatedTask);
    }
}
