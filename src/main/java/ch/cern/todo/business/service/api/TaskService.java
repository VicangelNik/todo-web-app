package ch.cern.todo.business.service.api;

import java.util.List;

import ch.cern.todo.business.model.Task;

public interface TaskService {

  Task createTask(Task task);

  List<Task> fetchTasks();

  void removeTask(long taskId);
}
