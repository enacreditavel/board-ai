package enalab.board_ai.application.input;

import enalab.board_ai.infrastructure.api.request.TaskRequest;
import org.springframework.ai.tool.annotation.ToolParam;

public record TaskInput(@ToolParam(description = "Descrição do tarefa") String description) {
    public static TaskInput toInput(TaskRequest taskRequest){
        return new TaskInput(taskRequest.description());
    }
}
