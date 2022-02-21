package org.matusikl.service;

import org.matusikl.exception.DataDuplicateException;
import org.matusikl.exception.DataNotFoundException;
import org.matusikl.model.Job;
import org.matusikl.repository.JobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class JobService {

    JobRepository jobRepository;
    private Logger logger = LoggerFactory.getLogger(JobService.class);

    @Autowired
    public JobService(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }

    public Job getJob(Integer id){
        logger.debug("In JobService getJob() method");
        Job job = jobRepository
                .findById(id)
                .orElseThrow(() -> {
                        DataNotFoundException exception = new DataNotFoundException(String.format("Get job failed! Cannot find job with id: %d in database!", id));
                        logger.error("Exception occured in getJob() id: {}", id, exception);
                        throw exception;
                });
        logger.info("Found job with id {}", id);
        return job;
    }

    public List<Job> getJobs(){
        logger.debug("In JobService getJobs() method");
        List<Job> jobList = jobRepository.findAll();
        if(jobList.isEmpty()){
            DataNotFoundException exception =  new DataNotFoundException("Get jobs failed! There are no jobs saved in database!");
            logger.error("Exception occured in getJobs()", exception);
            throw exception;
        }
        else{
            logger.info("Found list of jobs");
            return jobList;
        }
    }

    @Transactional
    public Job addJob(Job job){
        logger.debug("In JobService addJob() method");
        boolean jobExist = jobRepository
                .findByJob(job.getJob())
                .isPresent();
        if(jobExist){
            DataDuplicateException exception = new DataDuplicateException(String.format("Add job failed! Job with name: %s already exist in database!", job.getJob()));
            logger.error("Exception occured in addJob(): {}", exception);
            throw exception;
        }
        else{
            Job jobDB = jobRepository.save(job);
            logger.info("Job {} added successfully", jobDB);
            return jobDB;
        }
    }

    @Transactional
    public void deleteJob(Integer id){
        logger.debug("In JobService deleteJob() method");
        if(jobRepository.existsById(id)){
            jobRepository.deleteById(id);
            logger.info("Job id {} deleted successfully", id);
        }
        else{
            DataNotFoundException exception = new DataNotFoundException(String.format("Delete job failed There is no job with id: %d in database", id));
            logger.error("Exception occured in deleteJob() id: {} ", id, exception);
            throw exception;
        }
    }

    @Transactional
    public Job updateJob(Integer id, Job job){
        logger.debug("In JobService updateJob() method");
        Job jobDB = jobRepository
                .findById(id)
                .orElseThrow(() -> {
                        DataNotFoundException exception = new DataNotFoundException(String.format("Update job failed! There is no job with id: %d in database", id));
                        logger.error("Error occured in updateJob() findById()", exception);
                        throw exception;
                });
        boolean otherJobWithSameName = jobRepository
                .findByNameJobAndOtherId(job.getJob(), id)
                .isPresent();
        if(otherJobWithSameName){
            DataDuplicateException exception = new DataDuplicateException(String.format("Update job failed! There is already registered job with name: $s in database", job.getJob()));
            logger.error("Error occured in updateJob() findByNameJobAndOtherId()", exception);
            throw exception;
        }
        else{
            jobDB.setJob(job.getJob());
            jobRepository.save(jobDB);
            logger.info("Job id {} updated successfully", id);
            return jobDB;
        }
    }


}
