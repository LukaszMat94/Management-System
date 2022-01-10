package org.matusikl.repository;

import org.matusikl.model.Task;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Override
    @EntityGraph(value = "Task.employee", type = EntityGraph.EntityGraphType.LOAD)
    List<Task> findAll();
}
