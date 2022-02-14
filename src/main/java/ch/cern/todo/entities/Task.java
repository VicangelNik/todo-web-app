package ch.cern.todo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    @JsonIgnore
    private long taskId;
    @Column(name = "task_name")
    @NonNull
    private String taskName;
    @Column(name = "task_description")
    private String taskDescription;
    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deadline;
    @ManyToOne(fetch = FetchType.EAGER) // FetchType.LAZY creates a bug.
    @JoinColumn(name = "category_id")
    private TaskCategories taskCategories;

    public Task(String taskName, String taskDescription, Date deadline, TaskCategories taskCategories) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.deadline = deadline;
        this.taskCategories = taskCategories;
    }
}


