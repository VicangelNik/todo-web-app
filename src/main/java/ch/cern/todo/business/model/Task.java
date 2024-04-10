package ch.cern.todo.business.model;

import java.time.LocalDateTime;

import jakarta.annotation.Nullable;

public record Task(@Nullable Long id,
                   String name,
                   @Nullable String description,
                   LocalDateTime deadLine,
                   String categoryName) {
}
