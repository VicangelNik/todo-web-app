package ch.cern.todo.business.error;

import java.util.List;

public record ApiError(List<String> errors) {
}
