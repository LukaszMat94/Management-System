package org.matusikl.controller;

import org.matusikl.model.Job;
import org.matusikl.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@RestController
public class JobController {

    JobService jobService;

    @Autowired
    public JobController(JobService jobService){
        this.jobService = jobService;
    }

    @GetMapping(path = "/get/job/{id}", produces = "application/json")
    public ResponseEntity<Job> getJob(@PathVariable ("id") Integer id){
        Job job = jobService.getJob(id);
        return ResponseEntity
                .ok()
                .body(job);
    }

    @GetMapping(path = "/get/jobs", produces = "application/json")
    public ResponseEntity<List<Job>> getJobs(){
        List<Job> jobList = jobService.getJobs();
        return ResponseEntity
                .ok()
                .body(jobList);
    }

    @PostMapping(path = "/add/job", produces = "application/json")
    public ResponseEntity<Job> addJob(@RequestBody Job job){
        Job addedJob = jobService.addJob(job);
        return ResponseEntity
                .ok()
                .body(addedJob);
    }

    @DeleteMapping(path = "/delete/job/{id}", produces = {"application/json", "text/plain"})
    public ResponseEntity<String> deleteJob(@PathVariable ("id") Integer id){
        jobService.deleteJob(id);
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body("Job with id: " + id + " deleted");
    }

    @PutMapping("/update/job/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable ("id") Integer id,
                                         @RequestBody Job job){
        Job updatedJob = jobService.updateJob(id, job);
        return ResponseEntity
                .ok()
                .body(updatedJob);
    }
}
