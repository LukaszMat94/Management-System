package org.matusikl.mapperinterface;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.matusikl.dto.jobdto.JobGetDto;
import org.matusikl.dto.jobdto.JobPostDto;
import org.matusikl.model.Job;
import java.util.List;

@Mapper(componentModel = "spring")
public interface JobIMapper {

    JobIMapper INSTANCE = Mappers.getMapper( JobIMapper.class );

    JobGetDto jobToJobGetDto(Job job);

    Job jobPostDtoToJob(JobPostDto jobPostDto);

    List<JobGetDto> listJobToListJobGetDto(List<Job> jobList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateJobFromJobPostDto(JobPostDto jobPostDto, @MappingTarget Job job);

}