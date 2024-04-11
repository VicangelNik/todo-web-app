package ch.cern.todo.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.cern.todo.business.model.Category;
import ch.cern.todo.business.service.api.CategoryService;
import ch.cern.todo.rest.dto.request.CategoryRequest;
import ch.cern.todo.rest.dto.response.CategoryResponse;
import ch.cern.todo.rest.dto.response.TaskResponse;
import ch.cern.todo.rest.mapper.CategoryRequestModelMapper;
import ch.cern.todo.rest.mapper.TaskRequestModelMapper;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
final class CategoryController {

  private final CategoryService service;
  private final CategoryRequestModelMapper categoryMapper;
  private final TaskRequestModelMapper taskMapper;

  @Autowired
  public CategoryController(CategoryService service, CategoryRequestModelMapper categoryMapper,
                            TaskRequestModelMapper taskMapper) {
    this.service = service;
    this.categoryMapper = categoryMapper;
    this.taskMapper = taskMapper;
  }

  @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<CategoryResponse>> fetchCategories() {
    final List<CategoryResponse> responseList = service.fetchCategories().stream()
      .map(categoryMapper::mapToResponse)
      .toList();
    return ResponseEntity.ok(responseList);
  }

  @GetMapping(value = "/{id}/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<TaskResponse>> fetchTasksOfCategory(@PathVariable("id") long categoryId) {
    final List<TaskResponse> responseList = service.getTasksOfCategory(categoryId).stream()
      .map(taskMapper::mapToResponse)
      .toList();
    return ResponseEntity.ok(responseList);
  }

  @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CategoryResponse> saveTask(@Valid @RequestBody CategoryRequest request) {
    final Category category = categoryMapper.mapToModel(request);
    final Category saved = service.createCategory(category);
    return new ResponseEntity<>(categoryMapper.mapToResponse(saved), HttpStatus.CREATED);
  }


  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteCategory(@PathVariable("id") long categoryId) {
    service.removeCategory(categoryId);
    return ResponseEntity.ok().build();
  }
}
