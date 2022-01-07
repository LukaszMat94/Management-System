package org.matusikl.service;

import org.matusikl.exception.DataNotFoundException;
import org.matusikl.model.Task;
import org.matusikl.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TaskService {

    TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository){
        this.taskRepository =  taskRepository;
    }

    public Task getTask(Integer id){
        Task task = taskRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException(String.format("Get task failed! There is no task with id: %d in database", id)));
        return task;
    }

    public List<Task> getTasks(){
        List<Task> taskList = taskRepository.findAll();
        if(taskList.isEmpty()){
            throw new DataNotFoundException("Get tasks failed! There are no tasks in database");
        }
        else{
            return taskList;
        }
    }

    @Transactional
    public Task addTask(Task task){
        Task taskDB = taskRepository.save(task);
        return taskDB;
    }

    @Transactional
    public void deleteTask(Integer id){
        if(taskRepository.existsById(id)){
            taskRepository.deleteById(id);
        }
        else{
            throw new DataNotFoundException(String.format("Delete task failed!: There is no task with id: %d in database", id));
        }
    }

    @Transactional
    public Task updateTask(Integer id, Task task){
        Task taskDB = taskRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException(String.format("Update task failed! There is no task with id: %d in database", id)));
        taskDB.setNameTask(task.getNameTask());
        taskDB.setDescriptionTask(task.getDescriptionTask());
        taskDB.setStartDateTask(task.getStartDateTask());
        taskDB.setEndDateTask(task.getEndDateTask());
        taskRepository.save(taskDB);
        return taskDB;
    }

}
