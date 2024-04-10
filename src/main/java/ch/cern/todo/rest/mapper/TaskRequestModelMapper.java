package ch.cern.todo.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

import ch.cern.todo.business.model.Task;
import ch.cern.todo.rest.dto.request.TaskRequest;
import ch.cern.todo.rest.dto.response.TaskResponse;

@Mapper(componentModel = ComponentModel.SPRING)
public interface TaskRequestModelMapper {

  @Mapping(target = "id", ignore = true)
  Task mapToModel(TaskRequest request);

  TaskResponse mapToResponse(Task task);
}
