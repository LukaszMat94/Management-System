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
                .orElseThrow(() -> new DataNotFoundException("Get task failed! There is no task with id: " + id + " in database"));
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
        boolean existTask = taskRepository.existsById(id);
        if(existTask){
            taskRepository.deleteById(id);
        }
        else{
            throw new DataNotFoundException("Delete task failed!: There is no task with id: " + id + " in database");
        }
    }

    @Transactional
    public Task updateTask(Integer id, Task task){
        Task taskDB = taskRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException("Update task failed! There is no task with id: " + id + " in database"));
        taskDB.setNameTask(task.getNameTask());
        taskDB.setDescriptionTask(task.getDescriptionTask());
        taskDB.setStartDateTask(task.getStartDateTask());
        taskDB.setEndDateTask(task.getEndDateTask());
        taskRepository.save(taskDB);
        return taskDB;
    }

}
