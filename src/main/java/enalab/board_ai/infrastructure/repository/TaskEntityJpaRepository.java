package enalab.board_ai.infrastructure.repository;

import enalab.board_ai.infrastructure.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskEntityJpaRepository extends JpaRepository<TaskEntity, UUID> {
}
