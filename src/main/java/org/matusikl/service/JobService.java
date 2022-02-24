package org.matusikl.service;

import org.matusikl.dto.jobdto.JobGetDto;
import org.matusikl.dto.jobdto.JobPostDto;
import org.matusikl.exception.DataDuplicateException;
import org.matusikl.exception.DataNotFoundException;
import org.matusikl.mapperinterface.JobIMapper;
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
    JobIMapper jobIMapper;
    private Logger logger = LoggerFactory.getLogger(JobService.class);

    @Autowired
    public JobService(JobRepository jobRepository,
                      JobIMapper jobIMapper){
        this.jobRepository = jobRepository;
        this.jobIMapper = jobIMapper;
    }

    public JobGetDto getJob(Integer id){
        logger.debug("In JobService getJob() method id: {}", id);
        Job job = jobRepository
                .findById(id)
                .orElseThrow(() -> {
                        DataNotFoundException exception = new DataNotFoundException(String.format("Get job failed! Cannot find job with id: %d in database!", id));
                        logger.error("Exception occured in getJob() id: {}", id, exception);
                        throw exception;
                });
        logger.info("Found job: {} with id: {}", job, id);
        JobGetDto jobGetDto = jobIMapper.jobToJobGetDto(job);
        return jobGetDto;
    }

    public List<JobGetDto> getJobs(){
        logger.debug("In JobService getJobs() method");
        List<Job> jobList = jobRepository.findAll();
        if(jobList.isEmpty()){
            DataNotFoundException exception =  new DataNotFoundException("Get jobs failed! There are no jobs saved in database!");
            logger.error("Exception occured in getJobs()", exception);
            throw exception;
        }
        else{
            logger.info("Found list of jobs");
            List<JobGetDto> jobGetDtoList = jobIMapper.listJobToListJobGetDto(jobList);
            return jobGetDtoList;
        }
    }

    @Transactional
    public JobGetDto addJob(JobPostDto jobPostDto){
        logger.debug("In JobService addJob() method job: {}", jobPostDto);
        Job job = jobIMapper.jobPostDtoToJob(jobPostDto);
        boolean jobExist = jobRepository
                .findByJob(job.getJob())
                .isPresent();
        if(jobExist){
            DataDuplicateException exception = new DataDuplicateException(String.format("Add job failed! Job with name: %s already exist in database!", job.getJob()));
            logger.error("Exception occured in addJob() job: {}", job, exception);
            throw exception;
        }
        else{
            Job jobDB = jobRepository.save(job);
            logger.info("Job: {} added successfully", jobDB);
            JobGetDto jobGetDto = jobIMapper.jobToJobGetDto(jobDB);
            return jobGetDto;
        }
    }

    @Transactional
    public void deleteJob(Integer id){
        logger.debug("In JobService deleteJob() method id: {}", id);
        if(jobRepository.existsById(id)){
            jobRepository.deleteById(id);
            logger.info("Job id: {} deleted successfully", id);
        }
        else{
            DataNotFoundException exception = new DataNotFoundException(String.format("Delete job failed There is no job with id: %d in database", id));
            logger.error("Exception occured in deleteJob() id: {} ", id, exception);
            throw exception;
        }
    }

    @Transactional
    public JobGetDto updateJob(Integer id, JobPostDto jobPostDto){
        logger.debug("In JobService updateJob() method id: {} job: {}", id, jobPostDto);
        Job job = jobIMapper.jobPostDtoToJob(jobPostDto);
        Job jobDB = jobRepository
                .findById(id)
                .orElseThrow(() -> {
                        DataNotFoundException exception = new DataNotFoundException(String.format("Update job failed! There is no job with id: %d in database", id));
                        logger.error("Error occured in updateJob() findById() id: {} job: {}", id, job, exception);
                        throw exception;
                });
        boolean otherJobWithSameName = jobRepository
                .findByNameJobAndOtherId(job.getJob(), id)
                .isPresent();
        if(otherJobWithSameName){
            DataDuplicateException exception = new DataDuplicateException(String.format("Update job failed! There is already registered job with name: $s in database", job.getJob()));
            logger.error("Error occured in updateJob() findByNameJobAndOtherId() id: {} job: {}", id, jobDB, exception);
            throw exception;
        }
        else{
            jobIMapper.updateJobFromJobPostDto(jobPostDto, jobDB);
            jobRepository.save(jobDB);
            logger.info("Job: {} id: {} updated successfully", jobDB, id);
            JobGetDto jobGetDto = jobIMapper.jobToJobGetDto(jobDB);
            return jobGetDto;
        }
    }


}
