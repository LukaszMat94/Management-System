package org.matusikl.mapperinterface;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.matusikl.dto.taskdto.TaskGetDto;
import org.matusikl.dto.taskdto.TaskPostDto;
import org.matusikl.model.Task;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskIMapper {

    TaskIMapper INSTANCE = Mappers.getMapper( TaskIMapper.class );

    TaskGetDto taskToTaskGetDto(Task task);

    Task taskPostDtoToTask(TaskPostDto taskPostDto);

    List<TaskGetDto> listTaskToListTaskGetDto(List<Task> taskList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTaskFromTaskPostDto(TaskPostDto taskPostDto, @MappingTarget Task task);

}
