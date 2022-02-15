package ch.cern.todo.services;

import ch.cern.todo.entities.TaskCategory;
import ch.cern.todo.repositories.TaskCategoriesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TaskCategoryService {
    @Autowired
    TaskCategoriesRepository taskCategoriesRepository;

    public List<TaskCategory> getTaskCategories() {
        log.info("Retrieving all tasks categories");
        return taskCategoriesRepository.findAll();
    }

    public Optional<TaskCategory> getTaskCategoryById(Long id) {
        log.info("Retrieving task category with id {}", id);
        return taskCategoriesRepository.findById(id);
    }

    public TaskCategory saveTaskCategory(TaskCategory taskCategory) {
        log.info("Saving task category {}", taskCategory);
        return taskCategoriesRepository.save(taskCategory);
    }

    public void deleteTaskCategory(TaskCategory taskCategory) {
        log.info("Deleting task category {}", taskCategory);
        taskCategoriesRepository.delete(taskCategory);
    }
}
