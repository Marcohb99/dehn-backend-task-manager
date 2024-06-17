package com.mhb.dehn_backend_task_manager.Domain;

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

    List<Task> findAll();
}
