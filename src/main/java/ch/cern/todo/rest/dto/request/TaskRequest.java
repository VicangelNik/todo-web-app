package ch.cern.todo.rest.dto.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;

public record TaskRequest(@NotBlank String name,
                          @Nullable String description,
                          @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
                          @FutureOrPresent LocalDateTime deadLine,
                          @NotBlank String categoryName) {
}
