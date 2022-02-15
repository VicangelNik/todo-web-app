package ch.cern.todo.controllers;

import ch.cern.todo.entities.Task;
import ch.cern.todo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/get-all-tasks")
    public ResponseEntity<List<Task>> retrieveAllTasks() {
        List<Task> taskList = taskService.getTasks();
        if (taskList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(taskList, HttpStatus.FOUND);
    }

    @GetMapping("/get-task/{id}")
    public ResponseEntity<Task> retrieveTask(@PathVariable(value = "id", required = true) Long id) {
        Optional<Task> optionalTask = taskService.getTaskById(id);
        if (optionalTask.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalTask.get(), HttpStatus.FOUND);
    }

    @PostMapping(path = "create-task", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task updatedTask = taskService.saveTask(task);
        Task returnValue = taskService.getTaskById(updatedTask.getTaskId()).get();
        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @DeleteMapping("delete-task/{id}")
    public ResponseEntity<Long> deleteTask(@PathVariable Long id) {
        Optional<Task> optionalTask = taskService.getTaskById(id);

        if (optionalTask.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        taskService.deleteTask(optionalTask.get());

        optionalTask = taskService.getTaskById(id);

        if (optionalTask.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping(path = "update-task/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task tasks) {
        Optional<Task> optionalTask = taskService.getTaskById(id);
        if (optionalTask.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        optionalTask.ifPresent(task -> {
            if (tasks.getTaskName() != null) {
                task.setTaskName(tasks.getTaskName());
            }
            if (tasks.getTaskDescription() != null) {
                task.setTaskDescription(tasks.getTaskDescription());
            }
            if (tasks.getDeadline() != null) {
                task.setDeadline(tasks.getDeadline());
            }
            if (tasks.getTaskCategories() != null && tasks.getTaskCategories().getCategoryId() != null) {
                task.getTaskCategories().setCategoryId(tasks.getTaskCategories().getCategoryId());
            }
        });

        Task updatedTask = taskService.saveTask(optionalTask.get());
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }


}
