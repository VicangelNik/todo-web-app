package ch.cern.todo.rest;

import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ch.cern.todo.business.error.ApiError;
import ch.cern.todo.business.error.CategoryHasTasksException;
import ch.cern.todo.business.error.CategoryNotFoundException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionGlobalHandler {

  @ExceptionHandler({Exception.class})
  protected ResponseEntity<Object> handleException(Exception ex) {
    if (ex.getMessage() != null) {
      final var apiError = new ApiError(List.of(ex.getMessage()));
      return buildResponseEntity(INTERNAL_SERVER_ERROR, apiError);
    }
    return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(CategoryNotFoundException.class)
  protected ResponseEntity<Object> handleCategoryNotFoundException(
    CategoryNotFoundException ex) {
    final var apiError = new ApiError(List.of(ex.getMessage()));
    return buildResponseEntity(BAD_REQUEST, apiError);
  }

  @ExceptionHandler(CategoryHasTasksException.class)
  protected ResponseEntity<Object> handleCategoryNotFoundException(
    CategoryHasTasksException ex) {
    final var apiError = new ApiError(List.of(ex.getMessage()));
    return buildResponseEntity(BAD_REQUEST, apiError);
  }

  private ResponseEntity<Object> buildResponseEntity(final HttpStatusCode statusCode, final ApiError apiError) {
    return new ResponseEntity<>(apiError, statusCode);
  }
}
