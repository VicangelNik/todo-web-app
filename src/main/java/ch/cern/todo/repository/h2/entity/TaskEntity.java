package ch.cern.todo.repository.h2.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity(name = "tasks")
public class TaskEntity {

  @Id
  @Column(name = "task_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(name = "task_name")
  @NotNull
  @Size(max = 100)
  private String name;
  @Size(max = 500)
  @Column(name = "task_description")
  private String description;
  @Column(name = "task_deadline")
  @NotNull
  private @NotNull LocalDateTime deadLine;
  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  private CategoryEntity categoryEntity;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDateTime getDeadLine() {
    return deadLine;
  }

  public void setDeadLine(LocalDateTime deadLine) {
    this.deadLine = deadLine;
  }

  public CategoryEntity getCategoryEntity() {
    return categoryEntity;
  }

  public void setCategoryEntity(CategoryEntity categoryEntity) {
    this.categoryEntity = categoryEntity;
  }
}
