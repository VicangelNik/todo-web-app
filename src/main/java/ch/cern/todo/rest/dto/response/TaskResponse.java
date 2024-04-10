package ch.cern.todo.rest.dto.response;

import java.time.LocalDateTime;

public record TaskResponse(long id,
                           String name,
                           String description,
                           LocalDateTime deadLine,
                           String categoryName) {
}
