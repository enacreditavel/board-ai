package enalab.board_ai.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Builder
@Getter
public class Task {
    private UUID taskId;

    private String description;

    private TaskStatus taskStatus;

    private Instant createdAt;
}
