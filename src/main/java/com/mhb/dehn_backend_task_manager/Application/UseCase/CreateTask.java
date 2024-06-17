package com.mhb.dehn_backend_task_manager.Application.UseCase;

import com.mhb.dehn_backend_task_manager.Domain.Task;
import com.mhb.dehn_backend_task_manager.Domain.TaskRepository;
import com.mhb.dehn_backend_task_manager.Domain.TaskStatus;
import lombok.AllArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public final class CreateTask {
    private final TaskRepository taskRepository;

    public Task execute(
            String title,
            String description,
            String dueDate,
            String status
    ) throws IOException, ParseException {
        return this.taskRepository.create(title, description, dueDate, TaskStatus.fromString(status));
    }
}
