package ch.cern.todo.business.error;


public class CategoryHasTasksException extends RuntimeException{

  public CategoryHasTasksException(String message) {
    super(message);
  }
}
