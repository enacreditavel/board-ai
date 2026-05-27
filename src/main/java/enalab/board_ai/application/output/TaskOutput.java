package enalab.board_ai.application.output;

import enalab.board_ai.domain.Task;

import java.util.UUID;

public record TaskOutput(UUID taskId, String description, String taskStatus, String createdAt) {
    public static TaskOutput from(Task task){
        return new TaskOutput(task.getTaskId(), task.getDescription(), task.getTaskStatus().toString(), task.getCreatedAt().toString());
    }
}

