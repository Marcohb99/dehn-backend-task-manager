package com.mhb.dehn_backend_task_manager.Application.UseCase;

import com.mhb.dehn_backend_task_manager.Domain.Exception.TaskRepositoryException;
import com.mhb.dehn_backend_task_manager.Domain.Task;
import com.mhb.dehn_backend_task_manager.Domain.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ListTasks {
    private final TaskRepository taskRepository;

    public List<Task> execute() throws TaskRepositoryException {
        return taskRepository.findAll();
    }
}
