package ch.cern.todo;

import ch.cern.todo.entities.Task;
import ch.cern.todo.entities.TaskCategory;
import ch.cern.todo.repositories.TaskCategoriesRepository;
import ch.cern.todo.repositories.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class TaskRepositoryCommandLineRunner implements CommandLineRunner {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskCategoriesRepository taskCategoriesRepository;

    @Override
    public void run(String... args) throws Exception {
        TaskCategory taskCategories = new TaskCategory("Development", "Development lifecycle");
        TaskCategory taskCategories1 = new TaskCategory("Testing", "Testing lifecycle");

        TaskCategory newTaskCategories = taskCategoriesRepository.save(taskCategories);
        log.info("New task category is created : " + newTaskCategories);
        TaskCategory newTaskCategories1 = taskCategoriesRepository.save(taskCategories1);
        log.info("New task category is created : " + newTaskCategories1);

        Task task = new Task("implementation", "add new feature", new Date(), taskCategories);
        Task task1 = new Task("bug fixing", "Fix bug", new Date(), taskCategories);
        Task task2 = new Task("test implementation", "test newly develped implementation", new Date(), taskCategories1);
        Task task3 = new Task("reproduce error", "reproduce error reported by the client", new Date(), taskCategories1);

        Task NewTask = taskRepository.save(task);
        log.info("New task is created : " + NewTask);
        Task NewTask1 = taskRepository.save(task1);
        log.info("New task is created : " + NewTask1);
        Task NewTask2 = taskRepository.save(task2);
        log.info("New task is created : " + NewTask2);
        Task NewTask3 = taskRepository.save(task3);
        log.info("New task is created : " + NewTask3);
    }
}
