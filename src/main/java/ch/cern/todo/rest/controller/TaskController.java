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

import ch.cern.todo.business.model.Task;
import ch.cern.todo.rest.dto.request.TaskRequest;
import ch.cern.todo.rest.dto.response.TaskResponse;
import ch.cern.todo.rest.mapper.TaskRequestModelMapper;
import ch.cern.todo.business.service.api.TaskService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
final class TaskController {

  private final TaskService service;
  private final TaskRequestModelMapper mapper;

  @Autowired
  public TaskController(TaskService service, TaskRequestModelMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TaskResponse> saveTask(@Valid @RequestBody TaskRequest request) {
    final Task task = mapper.mapToModel(request);
    final Task saved = service.createTask(task);
    return new ResponseEntity<>(mapper.mapToResponse(saved), HttpStatus.CREATED);
  }

  @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<TaskResponse>> fetchTasks() {
    final List<TaskResponse> responseList = service.fetchTasks().stream().map(mapper::mapToResponse).toList();
    return ResponseEntity.ok(responseList);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteDepartmentById(@PathVariable("id") long taskId) {
    service.removeTask(taskId);
    return ResponseEntity.ok().build();
  }
}
