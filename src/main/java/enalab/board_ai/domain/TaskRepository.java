package enalab.board_ai.domain;

import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskRepository {
    Task save(Task task);

    Task findTaskById(UUID taskId);
}
