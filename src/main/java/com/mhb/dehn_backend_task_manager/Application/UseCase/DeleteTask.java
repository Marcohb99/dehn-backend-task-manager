package com.mhb.dehn_backend_task_manager.Application.UseCase;

import com.mhb.dehn_backend_task_manager.Domain.Exception.TaskNotFound;
import com.mhb.dehn_backend_task_manager.Domain.Exception.TaskRepositoryException;
import com.mhb.dehn_backend_task_manager.Domain.TaskRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteTask {
    private final TaskRepository taskRepository;

    public void execute(Integer taskId) throws TaskRepositoryException, TaskNotFound {
        this.taskRepository.delete(taskId);
    }
}
