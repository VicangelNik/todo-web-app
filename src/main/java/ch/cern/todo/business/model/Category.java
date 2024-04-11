package ch.cern.todo.business.model;

import java.util.Set;

import ch.cern.todo.repository.h2.entity.TaskEntity;
import jakarta.annotation.Nullable;

public record Category(@Nullable Long id,
                       String name,
                       String description,
                       Set<TaskEntity> taskEntities) {
}
