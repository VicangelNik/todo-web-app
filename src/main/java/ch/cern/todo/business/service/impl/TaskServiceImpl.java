package ch.cern.todo.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.cern.todo.business.model.Task;
import ch.cern.todo.business.service.api.TaskService;
import ch.cern.todo.business.mapper.TaskToEntityMapper;
import ch.cern.todo.repository.h2.TaskRepository;
import ch.cern.todo.repository.h2.entity.TaskEntity;

@Service
final class TaskServiceImpl implements TaskService {

  private final TaskRepository repository;
  private final TaskToEntityMapper mapper;

  @Autowired
  public TaskServiceImpl(TaskRepository repository, TaskToEntityMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public Task createTask(Task task) {
    final TaskEntity entity = mapper.taskToEntity(task);
    final TaskEntity saved = repository.save(entity);
    return mapper.entityToModel(saved);
  }

  @Override
  public List<Task> fetchTasks() {
    return repository.findAll().stream().map(mapper::entityToModel).toList();
  }

  @Override
  public void removeTask(long taskId) {
    repository.deleteById(taskId);
  }
}
