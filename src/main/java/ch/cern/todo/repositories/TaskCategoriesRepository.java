package ch.cern.todo.repositories;

import ch.cern.todo.entities.TaskCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskCategoriesRepository extends JpaRepository<TaskCategory, Long> {}
