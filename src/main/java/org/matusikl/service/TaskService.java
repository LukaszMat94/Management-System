package org.matusikl.service;

import org.matusikl.exception.DataNotFoundException;
import org.matusikl.model.Task;
import org.matusikl.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TaskService {

    TaskRepository taskRepository;
    private Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    public TaskService(TaskRepository taskRepository){
        this.taskRepository =  taskRepository;
    }

    public Task getTask(Integer id){
        logger.debug("In TaskService getTask() method");
        Task task = taskRepository
                .findById(id)
                .orElseThrow(() -> {
                        DataNotFoundException exception = new DataNotFoundException(String.format("Get task failed! There is no task with id: %d in database", id));
                        logger.error("Exception occured in getTask() id: {}", id, exception);
                        throw exception;
                });
        logger.info("Found task with id {}", id);
        return task;
    }

    public List<Task> getTasks(){
        logger.debug("In TaskService getTasks() method");
        List<Task> taskList = taskRepository.findAll();
        if(taskList.isEmpty()){
            DataNotFoundException exception =  new DataNotFoundException("Get tasks failed! There are no tasks in database");
            logger.error("Exception occured in getTasks()", exception);
            throw exception;
        }
        else{
            logger.info("Found list of tasks");
            return taskList;
        }
    }

    @Transactional
    public Task addTask(Task task){
        logger.debug("In TaskService addTask() method");
        Task taskDB = taskRepository.save(task);
        logger.info("Task {} added successfully", taskDB);
        return taskDB;
    }

    @Transactional
    public void deleteTask(Integer id){
        logger.debug("In TaskService deleteTask() method");
        if(taskRepository.existsById(id)){
            taskRepository.deleteById(id);
            logger.info("Task id {} deleted successfully", id);
        }
        else{
            DataNotFoundException exception = new DataNotFoundException(String.format("Delete task failed!: There is no task with id: %d in database", id));
            logger.error("Exception occured in deleteTask() id: {} ", id, exception);
            throw exception;
        }
    }

    @Transactional
    public Task updateTask(Integer id, Task task){
        logger.debug("In TaskService updateTask() method");
        Task taskDB = taskRepository
                .findById(id)
                .orElseThrow(() -> {
                        DataNotFoundException exception = new DataNotFoundException(String.format("Update task failed! There is no task with id: %d in database", id));
                        logger.error("Error occured in updateTask() findById()", exception);
                        throw exception;
                });
        taskDB.setNameTask(task.getNameTask());
        taskDB.setDescriptionTask(task.getDescriptionTask());
        taskDB.setStartDateTask(task.getStartDateTask());
        taskDB.setEndDateTask(task.getEndDateTask());
        taskRepository.save(taskDB);
        logger.info("Task id {} updated successfully", id);
        return taskDB;
    }
}
