package com.mhb.dehn_backend_task_manager.Application.UseCase;

import com.mhb.dehn_backend_task_manager.Domain.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public final class CreateTask {
    private final TaskRepository taskRepository;

    public void execute(String title, String description, String dueDate) {
        this.taskRepository.create(title, description, dueDate);
    }
}
