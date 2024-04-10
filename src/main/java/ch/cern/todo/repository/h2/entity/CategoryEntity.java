package ch.cern.todo.repository.h2.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity(name = "task_category")
public class CategoryEntity {

  @Id
  @Column(name = "category_id")
  private Long id;
  @Column(name = "category_name", unique = true)
  @NotNull
  @Size(max = 100)
  private String name;
  @Size(max = 500)
  @Column(name = "category_description")
  private String description;

  @OneToMany(mappedBy = "categoryEntity")
  private Set<TaskEntity> taskEntities;

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

  public Set<TaskEntity> getTaskEntities() {
    return taskEntities;
  }

  public void setTaskEntities(Set<TaskEntity> taskEntities) {
    this.taskEntities = taskEntities;
  }
}
