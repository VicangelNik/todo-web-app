package ch.cern.todo.rest.dto.response;

public record CategoryResponse(long id,
                               String name,
                               String description) {
}
