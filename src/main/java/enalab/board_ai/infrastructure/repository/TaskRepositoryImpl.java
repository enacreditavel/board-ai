package enalab.board_ai.infrastructure.repository;

import enalab.board_ai.domain.Task;
import enalab.board_ai.domain.TaskRepository;
import enalab.board_ai.infrastructure.entity.TaskEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private final TaskEntityJpaRepository taskEntityJpaRepository;

    @Override
    public Task save(Task task) {
        return taskEntityJpaRepository.save(TaskEntity.from(task)).toDomain();
    }

    @Override
    public Task findTaskById(UUID taskId) {
        return null;
    }
}
