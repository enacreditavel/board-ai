package enalab.board_ai.infrastructure.api.response;

import enalab.board_ai.application.output.TaskOutput;

import java.util.UUID;

public record TaskResponse(UUID taskId, String description, String taskStatus, String createdAt) {
    public static TaskResponse from(TaskOutput taskOutput){
        return new TaskResponse(taskOutput.taskId(), taskOutput.description(), taskOutput.taskStatus(), taskOutput.createdAt());
    }
}
