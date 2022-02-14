package ch.cern.todo.services;

import ch.cern.todo.entities.Task;
import ch.cern.todo.repositories.TaskRepository;
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
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/get-all-tasks")
    public ResponseEntity<List<Task>> retrieveTasks() {
        log.info("Retrieving all tasks");
        List<Task> taskList = taskRepository.findAll();
        if (taskList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(taskList, HttpStatus.FOUND);
    }

    @GetMapping("/get-task/{id}")
    public ResponseEntity<Task> retrieveTask(@PathVariable(value = "id", required = true) String id) {
        log.info("Retrieving task with id {}", id);
        Optional<Task> optionalTask = taskRepository.findById(Long.valueOf(id));
        if (optionalTask.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalTask.get(), HttpStatus.FOUND);
    }

    @PostMapping(path = "create-task", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task updatedTask = taskRepository.save(task);
        Task returnValue = taskRepository.findById(updatedTask.getTaskId()).get();
        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @DeleteMapping("delete-task/{id}")
    public ResponseEntity<Long> deleteTask(@PathVariable Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        Task task = optionalTask.orElseThrow(() -> new RuntimeException("Unable to Find task with id" + id));

        taskRepository.delete(task);

        optionalTask = taskRepository.findById(id);

        if (optionalTask.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping(path = "update-task/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task tasks) {
        Optional<Task> optionalTask = taskRepository.findById(id);
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

        Task updatedTask = taskRepository.save(optionalTask.get());
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }


}
