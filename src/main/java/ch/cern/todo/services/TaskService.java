package ch.cern.todo.services;

import ch.cern.todo.entities.Task;
import ch.cern.todo.repositories.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public List<Task> getTasks() {
        log.info("Retrieving all tasks");
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        log.info("Retrieving task with id {}", id);
        return taskRepository.findById(id);
    }

    public Task saveTask(Task task) {
        log.info("Saving task {}", task);
        return taskRepository.save(task);
    }

    public void deleteTask(Task task) {
        log.info("Deleting task {}", task);
        taskRepository.delete(task);
    }
}
