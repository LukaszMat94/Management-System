package org.matusikl.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get job by id", description = "Get simple job by specified id", tags = "Job")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Job with specified id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Job.class))}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content)})
    @GetMapping(path = "/jobs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Job> getJob(@PathVariable ("id") Integer id){
        Job job = jobService.getJob(id);
        return ResponseEntity
                .ok()
                .body(job);
    }

    @Operation(summary = "Get jobs", description = "Get list of jobs", tags = "Job")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the list of jobs",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Job.class))}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content)})
    @GetMapping(path = "/jobs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Job>> getJobs(){
        List<Job> jobList = jobService.getJobs();
        return ResponseEntity
                .ok()
                .body(jobList);
    }

    @Operation(summary = "Save job", description = "Save specified job", tags = "Job")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Saved specified job",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Job.class))}),
            @ApiResponse(responseCode = "409", description = "Error: duplicate - data already exist in database",
                    content = @Content)})
    @PostMapping(path = "/jobs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Job> addJob(@RequestBody Job job){
        Job addedJob = jobService.addJob(job);
        return ResponseEntity
                .ok()
                .body(addedJob);
    }

    @Operation(summary = "Delete job by id", description = "Delete simple job by specified id", tags = "Job")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted Job with specified id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Job.class))}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content)})
    @DeleteMapping(path = "/jobs/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> deleteJob(@PathVariable ("id") Integer id){
        jobService.deleteJob(id);
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(String.format("Job with id: %d deleted", id));
    }

    @Operation(summary = "Update job by id", description = "Update simple job by specified id", tags = "Job")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated job with specified id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Job.class))}),
            @ApiResponse(responseCode = "404", description = "Error: not found in database",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Error: validation of attributes",
                    content = @Content)})
    @PutMapping(path = "/jobs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Job> updateJob(@PathVariable ("id") Integer id,
                                         @RequestBody Job job){
        Job updatedJob = jobService.updateJob(id, job);
        return ResponseEntity
                .ok()
                .body(updatedJob);
    }
}
