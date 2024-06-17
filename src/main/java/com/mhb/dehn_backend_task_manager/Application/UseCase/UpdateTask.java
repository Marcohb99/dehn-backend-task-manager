package com.mhb.dehn_backend_task_manager.Application.UseCase;

import com.mhb.dehn_backend_task_manager.Domain.Task;
import com.mhb.dehn_backend_task_manager.Domain.TaskRepository;
import com.mhb.dehn_backend_task_manager.Domain.TaskStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateTask {
    private final TaskRepository taskRepository;
    public void execute(
            Integer taskId,
            String title,
            String description,
            String dueDate,
            String status
    ) {
        Task task = new Task(
                taskId,
                title,
                description,
                dueDate,
                TaskStatus.fromString(status)
        );
        this.taskRepository.update(task);
    }
}
