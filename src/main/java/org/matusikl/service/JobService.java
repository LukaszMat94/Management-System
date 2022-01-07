package org.matusikl.service;

import org.matusikl.exception.DataDuplicateException;
import org.matusikl.exception.DataNotFoundException;
import org.matusikl.model.Job;
import org.matusikl.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class JobService {

    JobRepository jobRepository;

    @Autowired
    public JobService(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }

    public Job getJob(Integer id){
        Job job = jobRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException(String.format("Get job failed! Cannot find job with id: %d in database!", id)));
        return job;
    }

    public List<Job> getJobs(){
        List<Job> jobList = jobRepository.findAll();
        if(jobList.isEmpty()){
            throw new DataNotFoundException("Get jobs failed! There are no jobs saved in database!");
        }
        else{
            return jobList;
        }
    }

    @Transactional
    public Job addJob(Job job){
        boolean jobExist = jobRepository
                .findByJob(job.getJob())
                .isPresent();
        if(jobExist){
            throw new DataDuplicateException(String.format("Add job failed! Job with name: %s already exist in database!", job.getJob()));
        }
        else{
            Job newJob = jobRepository.save(job);
            return newJob;
        }
    }

    @Transactional
    public void deleteJob(Integer id){
        if(jobRepository.existsById(id)){
            jobRepository.deleteById(id);
        }
        else{
            throw new DataNotFoundException(String.format("Delete job failed There is no job with id: %d in database", id));
        }
    }

    @Transactional
    public Job updateJob(Integer id, Job job){
        Job jobDB = jobRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException(String.format("Update job failed! There is no job with id: %d in database", id)));
        boolean otherJobWithSameName = jobRepository
                .findByNameJobAndOtherId(job.getJob(), id)
                .isPresent();
        if(otherJobWithSameName){
            throw new DataDuplicateException(String.format("Update job failed! There is already registered job with name: $s in database", job.getJob()));
        }
        else{
            jobDB.setJob(job.getJob());
            jobRepository.save(jobDB);
            return jobDB;
        }
    }


}
