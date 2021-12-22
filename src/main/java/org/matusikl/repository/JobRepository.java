package org.matusikl.repository;

import org.matusikl.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {

    Optional<Job> findByJob(String nameJob);

    @Query("select j from Job j where j.job = ?1 and j.id <> ?2")
    Optional<Job> findByNameJobAndOtherId(String jobName, Integer idJob);
}
