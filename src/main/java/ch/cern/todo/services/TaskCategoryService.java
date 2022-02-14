package ch.cern.todo.services;

import ch.cern.todo.entities.TaskCategories;
import ch.cern.todo.repositories.TaskCategoriesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class TaskCategoryService {
    @Autowired
    TaskCategoriesRepository taskCategoriesRepository;

    @GetMapping("/get-all-task_categories")
    public ResponseEntity<List<TaskCategories>> retrieveTaskCategories() {
        log.info("Retrieving all tasks categories");
        List<TaskCategories> taskCategoriesList = taskCategoriesRepository.findAll();
        if (taskCategoriesList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(taskCategoriesList, HttpStatus.FOUND);
    }

    @GetMapping("/get-task_category/{id}")
    public ResponseEntity<TaskCategories> retrieveTaskCategory(@PathVariable(value = "id", required = true) String id) {
        log.info("Retrieving task category with id {}", id);
        Optional<TaskCategories> optionalTaskCategories = taskCategoriesRepository.findById(Long.valueOf(id));
        if (optionalTaskCategories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalTaskCategories.get(), HttpStatus.FOUND);
    }

    @PostMapping(path = "create-task_category", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskCategories> createTaskCategory(@RequestBody TaskCategories taskCategories) {
        TaskCategories updatedTaskCategories = taskCategoriesRepository.save(taskCategories);

        TaskCategories returnValue = taskCategoriesRepository.findById(updatedTaskCategories.getCategoryId()).get();
        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @DeleteMapping("delete-task_category/{id}")
    public ResponseEntity<Long> deleteTaskCategory(@PathVariable Long id) {
        Optional<TaskCategories> optionalTaskCategories = taskCategoriesRepository.findById(id);

        if (optionalTaskCategories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        taskCategoriesRepository.delete(optionalTaskCategories.get());

        optionalTaskCategories = taskCategoriesRepository.findById(id);

        if (optionalTaskCategories.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping(path = "update-task_category/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskCategories> updateTaskCategory(@PathVariable Long id,
                                                             @RequestBody TaskCategories taskCategories) {
        Optional<TaskCategories> optionalTaskCategories = taskCategoriesRepository.findById(id);
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
        TaskCategories updatedTaskCategory = taskCategoriesRepository.save(optionalTaskCategories.get());
        return new ResponseEntity<>(updatedTaskCategory, HttpStatus.OK);
    }
}
