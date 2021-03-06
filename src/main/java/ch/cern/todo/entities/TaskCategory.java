package ch.cern.todo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity(name = "task_categories")
@NoArgsConstructor
public class TaskCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    @JsonBackReference
    private Long categoryId;
    @NotBlank(message = "Category name is mandatory")
    @Column(name = "category_name", unique = true)
    private String categoryName;
    @Column(name = "category_description")
    private String categoryDescription;

    public TaskCategory(String categoryName, String categoryDescription) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }
}
