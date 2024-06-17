package com.mhb.dehn_backend_task_manager.Application.UseCase;

import com.mhb.dehn_backend_task_manager.Domain.Task;
import com.mhb.dehn_backend_task_manager.Domain.TaskStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListTasks {
    public List<Task> execute() {
        return List.of(
                new Task(1, "Task 1", "Description 1", "2021-01-01", TaskStatus.COMPLETED),
                new Task(2, "Task 2", "Description 2", "2021-01-02", TaskStatus.PENDING),
                new Task(3, "Task 3", "Description 3", "2021-01-03", TaskStatus.PENDING)
        );
    }
}
