package ch.cern.todo.business.service.api;

import java.util.List;

import ch.cern.todo.business.model.Category;
import ch.cern.todo.business.model.Task;

public interface CategoryService {

  List<Category> fetchCategories();

  List<Task> getTasksOfCategory(long categoryId);

  Category createCategory(Category category);

  void removeCategory(long categoryId);
}
