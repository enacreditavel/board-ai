package enalab.board_ai.infrastructure.entity;

import enalab.board_ai.domain.Task;
import enalab.board_ai.domain.TaskStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tasks")
public class TaskEntity {
    @Id
    private UUID taskId;

    private String description;

    private String taskStatus;

    private Instant createdAt;

    public static TaskEntity from(Task task){
        return TaskEntity.builder()
                .taskId(task.getTaskId())
                .description(task.getDescription())
                .taskStatus(task.getTaskStatus().toString())
                .createdAt(task.getCreatedAt())
                .build();
    }

    public Task toDomain() {
        return Task.builder()
                .taskId(this.taskId)
                .description(this.description)
                .taskStatus(TaskStatus.valueOf(this.taskStatus))
                .createdAt(this.createdAt)
                .build();
    }
}
