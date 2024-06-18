package com.mhb.dehn_backend_task_manager.Domain;

import com.mhb.dehn_backend_task_manager.Domain.Exception.TaskNotFound;
import com.mhb.dehn_backend_task_manager.Domain.Exception.TaskRepositoryException;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface TaskRepository {
    Task create(
            String title,
            String description,
            String dueDate,
            TaskStatus status
    ) throws TaskRepositoryException;

    List<Task> findAll() throws TaskRepositoryException;

    void update(Task task) throws TaskRepositoryException, TaskNotFound;

    void delete(Integer taskId) throws TaskRepositoryException, TaskNotFound;;
}
