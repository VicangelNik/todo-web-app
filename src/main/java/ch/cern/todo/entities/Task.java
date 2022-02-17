package ch.cern.todo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Entity(name = "tasks")
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    @JsonIgnore
    private long taskId;
    @Column(name = "task_name")
    @NotBlank(message = "Task name is mandatory")
    private String taskName;
    @Column(name = "task_description")
    private String taskDescription;
    @NotBlank(message = "deadline is mandatory")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deadline;
    @ManyToOne(fetch = FetchType.EAGER) // FetchType.LAZY creates a bug.
    @JoinColumn(name = "category_id")
    private TaskCategory taskCategories;

    public Task(String taskName, String taskDescription, Date deadline, TaskCategory taskCategories) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.deadline = deadline;
        this.taskCategories = taskCategories;
    }
}


