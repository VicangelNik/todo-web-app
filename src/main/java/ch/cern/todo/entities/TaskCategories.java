package ch.cern.todo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@Entity(name = "task_categories")
@NoArgsConstructor
public class TaskCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    @JsonBackReference
    private Long categoryId;
    @NonNull
    @Column(name = "category_name", unique = true)
    private String categoryName;
    @Column(name = "category_description")
    private String categoryDescription;

    public TaskCategories(String categoryName, String categoryDescription) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }
}
