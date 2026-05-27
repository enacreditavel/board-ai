package enalab.board_ai.application;

import enalab.board_ai.application.input.TaskInput;
import enalab.board_ai.application.output.TaskOutput;
import enalab.board_ai.domain.Task;
import enalab.board_ai.domain.TaskRepository;
import enalab.board_ai.domain.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CreateTaskUseCase {
    private final TaskRepository taskRepository;

    @Tool(name = "create-task", description = "Cria uma nova tarefa")
    public TaskOutput execute(TaskInput taskInput){
        Task task = Task.builder()
                .taskId(UUID.randomUUID())
                .description(taskInput.description())
                .taskStatus(TaskStatus.TO_DO)
                .createdAt(Instant.now())
                .build();
        return TaskOutput.from(taskRepository.save(task));
    }

}
