package org.matusikl.service;

import org.matusikl.dto.taskdto.TaskGetDto;
import org.matusikl.dto.taskdto.TaskPostDto;
import org.matusikl.exception.DataNotFoundException;
import org.matusikl.mapperinterface.TaskIMapper;
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
    TaskIMapper taskIMapper;
    private Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    public TaskService(TaskRepository taskRepository,
                       TaskIMapper taskIMapper){
        this.taskRepository = taskRepository;
        this.taskIMapper = taskIMapper;
    }

    public TaskGetDto getTask(Integer id){
        logger.debug("In TaskService getTask() method id: {}", id);
        Task task = taskRepository
                .findById(id)
                .orElseThrow(() -> {
                        DataNotFoundException exception = new DataNotFoundException(String.format("Get task failed! There is no task with id: %d in database", id));
                        logger.error("Exception occured in getTask() id: {}", id, exception);
                        throw exception;
                });
        logger.info("Found task: {} with id: {}", task, id);
        TaskGetDto taskGetDto = taskIMapper.taskToTaskGetDto(task);
        return taskGetDto;
    }

    public List<TaskGetDto> getTasks(){
        logger.debug("In TaskService getTasks() method");
        List<Task> taskList = taskRepository.findAll();
        if(taskList.isEmpty()){
            DataNotFoundException exception =  new DataNotFoundException("Get tasks failed! There are no tasks in database");
            logger.error("Exception occured in getTasks()", exception);
            throw exception;
        }
        else{
            logger.info("Found list of tasks");
            List<TaskGetDto> taskGetDtoList = taskIMapper.listTaskToListTaskGetDto(taskList);
            return taskGetDtoList;
        }
    }

    @Transactional
    public TaskGetDto addTask(TaskPostDto taskPostDto){
        logger.debug("In TaskService addTask() method task: {}", taskPostDto);
        Task task = taskIMapper.taskPostDtoToTask(taskPostDto);
        Task taskDB = taskRepository.save(task);
        logger.info("Task: {} added successfully", taskDB);
        TaskGetDto taskGetDto = taskIMapper.taskToTaskGetDto(taskDB);
        return taskGetDto;
    }

    @Transactional
    public void deleteTask(Integer id){
        logger.debug("In TaskService deleteTask() method id: {}", id);
        if(taskRepository.existsById(id)){
            taskRepository.deleteById(id);
            logger.info("Task id: {} deleted successfully", id);
        }
        else{
            DataNotFoundException exception = new DataNotFoundException(String.format("Delete task failed!: There is no task with id: %d in database", id));
            logger.error("Exception occured in deleteTask() id: {} ", id, exception);
            throw exception;
        }
    }

    @Transactional
    public TaskGetDto updateTask(Integer id, TaskPostDto taskPostDto){
        logger.debug("In TaskService updateTask() method task: {} id: {}", taskPostDto, id);
        Task taskDB = taskRepository
                .findById(id)
                .orElseThrow(() -> {
                        DataNotFoundException exception = new DataNotFoundException(String.format("Update task failed! There is no task with id: %d in database", id));
                        logger.error("Error occured in updateTask() findById() task: {} id: {}", taskPostDto, id, exception);
                        throw exception;
                });
        taskIMapper.updateTaskFromTaskPostDto(taskPostDto, taskDB);
        taskRepository.save(taskDB);
        logger.info("Task: {} id: {} updated successfully", taskDB, id);
        TaskGetDto taskGetDto = taskIMapper.taskToTaskGetDto(taskDB);
        return taskGetDto;
    }

}
