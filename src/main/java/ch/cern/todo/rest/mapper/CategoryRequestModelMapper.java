package ch.cern.todo.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

import ch.cern.todo.business.model.Category;
import ch.cern.todo.rest.dto.request.CategoryRequest;
import ch.cern.todo.rest.dto.response.CategoryResponse;

@Mapper(componentModel = ComponentModel.SPRING)
public interface CategoryRequestModelMapper {

  CategoryResponse mapToResponse(Category category);

  @Mapping(target = "id", ignore = true)
  Category mapToModel(CategoryRequest request);
}
