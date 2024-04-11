package ch.cern.todo.business.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

import ch.cern.todo.business.model.Category;
import ch.cern.todo.repository.h2.entity.CategoryEntity;

@Mapper(componentModel = ComponentModel.SPRING)
public interface CategoryModelEntityMapper {

  Category entityToModel(CategoryEntity entity);

  @Mapping(target = "id", ignore = true)
  CategoryEntity categoryToEntity(Category category);
}
