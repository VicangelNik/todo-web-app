package ch.cern.todo.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.cern.todo.business.error.CategoryHasTasksException;
import ch.cern.todo.business.error.CategoryNotFoundException;
import ch.cern.todo.business.mapper.CategoryModelEntityMapper;
import ch.cern.todo.business.mapper.TaskToEntityMapper;
import ch.cern.todo.business.model.Category;
import ch.cern.todo.business.model.Task;
import ch.cern.todo.business.service.api.CategoryService;
import ch.cern.todo.repository.h2.TaskCategoryRepository;
import ch.cern.todo.repository.h2.entity.CategoryEntity;

@Service
final class CategoryServiceImpl implements CategoryService {

  private final TaskCategoryRepository repository;
  private final CategoryModelEntityMapper categoryMapper;
  private final TaskToEntityMapper taskMapper;

  @Autowired
  public CategoryServiceImpl(TaskCategoryRepository repository,
                             CategoryModelEntityMapper categoryMapper,
                             TaskToEntityMapper taskMapper) {
    this.repository = repository;
    this.categoryMapper = categoryMapper;
    this.taskMapper = taskMapper;
  }

  @Override
  public List<Category> fetchCategories() {
    return repository.findAll().stream()
      .map(categoryMapper::entityToModel)
      .toList();
  }

  @Override
  public List<Task> getTasksOfCategory(long categoryId) {
    return repository.findById(categoryId)
      .orElseThrow(() -> new CategoryNotFoundException("Category for given id: " + categoryId + "could not be found"))
      .getTaskEntities()
      .stream().map(taskMapper::entityToModel)
      .toList();
  }

  @Override
  public Category createCategory(final Category category) {
    final CategoryEntity entity = categoryMapper.categoryToEntity(category);
    final CategoryEntity saved = repository.save(entity);
    return categoryMapper.entityToModel(saved);
  }

  @Override
  public void removeCategory(long categoryId) {
    if (!getTasksOfCategory(categoryId).isEmpty()) {
      throw new CategoryHasTasksException("Category with id " + categoryId + " contains tasks and can not be deleted.\n" +
                                          "Please delete the tasks first.");
    }
    repository.deleteById(categoryId);
  }
}
