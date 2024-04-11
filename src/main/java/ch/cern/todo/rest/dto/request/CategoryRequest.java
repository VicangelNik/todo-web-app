package ch.cern.todo.rest.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(@NotBlank String name,
                              String description) {
}
