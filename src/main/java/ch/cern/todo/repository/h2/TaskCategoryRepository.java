package ch.cern.todo.repository.h2;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.cern.todo.repository.h2.entity.CategoryEntity;

@Repository
public interface TaskCategoryRepository extends JpaRepository<CategoryEntity, Long> {

  Optional<CategoryEntity> findByName(String name);
}
