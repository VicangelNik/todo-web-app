package ch.cern.todo.controllers;

import ch.cern.todo.entities.TaskCategory;
import ch.cern.todo.services.TaskCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TaskCategoryController {

    @Autowired
    TaskCategoryService taskCategoryService;

    @GetMapping("/get-all-task_categories")
    public ResponseEntity<List<TaskCategory>> retrieveTaskCategories() {
        List<TaskCategory> taskCategoriesList = taskCategoryService.getTaskCategories();
        if (taskCategoriesList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(taskCategoriesList, HttpStatus.FOUND);
    }

    @GetMapping("/get-task_category/{id}")
    public ResponseEntity<TaskCategory> retrieveTaskCategory(@PathVariable(value = "id", required = true) Long id) {
        Optional<TaskCategory> optionalTaskCategories = taskCategoryService.getTaskCategoryById(id);
        if (optionalTaskCategories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalTaskCategories.get(), HttpStatus.FOUND);
    }

    @PostMapping(path = "create-task_category", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskCategory> createTaskCategory(@RequestBody TaskCategory taskCategory) {
        TaskCategory updatedTaskCategories = taskCategoryService.saveTaskCategory(taskCategory);

        TaskCategory returnValue = taskCategoryService.getTaskCategoryById(updatedTaskCategories.getCategoryId()).get();
        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @DeleteMapping("delete-task_category/{id}")
    public ResponseEntity<Long> deleteTaskCategory(@PathVariable Long id) {
        Optional<TaskCategory> optionalTaskCategories = taskCategoryService.getTaskCategoryById(id);

        if (optionalTaskCategories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        taskCategoryService.deleteTaskCategory(optionalTaskCategories.get());

        optionalTaskCategories = taskCategoryService.getTaskCategoryById(id);

        if (optionalTaskCategories.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping(path = "update-task_category/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskCategory> updateTaskCategory(@PathVariable Long id,
                                                           @RequestBody TaskCategory taskCategories) {
        Optional<TaskCategory> optionalTaskCategories = taskCategoryService.getTaskCategoryById(id);
        if (optionalTaskCategories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        optionalTaskCategories.ifPresent(task -> {
            if (taskCategories.getCategoryName() != null) {
                task.setCategoryName(taskCategories.getCategoryName());
            }
            if (taskCategories.getCategoryDescription() != null) {
                task.setCategoryDescription(taskCategories.getCategoryDescription());
            }
        });
        TaskCategory updatedTaskCategory = taskCategoryService.saveTaskCategory(optionalTaskCategories.get());
        return new ResponseEntity<>(updatedTaskCategory, HttpStatus.OK);
    }
}
