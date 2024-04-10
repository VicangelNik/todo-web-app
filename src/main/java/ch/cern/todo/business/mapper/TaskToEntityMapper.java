package ch.cern.todo.business.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

import ch.cern.todo.business.error.CategoryNotFoundException;
import ch.cern.todo.business.model.Task;
import ch.cern.todo.repository.h2.TaskCategoryRepository;
import ch.cern.todo.repository.h2.entity.CategoryEntity;
import ch.cern.todo.repository.h2.entity.TaskEntity;

@Mapper(componentModel = ComponentModel.SPRING)
public abstract class TaskToEntityMapper {

  @Autowired
  private TaskCategoryRepository repository;

  @Mapping(target = "id", ignore = true)
  @Mapping(source = "categoryName", target = "categoryEntity", qualifiedByName = "mapToCategoryEntity")
  public abstract TaskEntity taskToEntity(Task task);

  @Named("mapToCategoryEntity")
  CategoryEntity mapToCategoryEntity(@NonNull String categoryName) {
    return repository.findByName(categoryName).orElseThrow(() -> new CategoryNotFoundException("Category for given name: " + categoryName + "could not be found"));
  }

  @Mapping(target = "categoryName", source = "categoryEntity.name")
  public abstract Task entityToModel(TaskEntity entity);
}
