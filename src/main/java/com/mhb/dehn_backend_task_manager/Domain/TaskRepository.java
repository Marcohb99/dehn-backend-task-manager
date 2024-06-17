package com.mhb.dehn_backend_task_manager.Domain;

import com.mhb.dehn_backend_task_manager.Domain.Exception.TaskNotFound;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface TaskRepository {
    Task create(
            String title,
            String description,
            String dueDate,
            TaskStatus status
    ) throws IOException, ParseException;

    List<Task> findAll() throws IOException, ParseException;

    void update(Task task) throws IOException, ParseException, TaskNotFound;

    void delete(Integer taskId) throws IOException, ParseException, TaskNotFound;;
}
