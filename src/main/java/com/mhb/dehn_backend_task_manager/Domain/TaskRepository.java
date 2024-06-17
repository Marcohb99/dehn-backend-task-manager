package com.mhb.dehn_backend_task_manager.Domain;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface TaskRepository {
    public Task create(String title, String description, String dueDate) throws IOException, ParseException;
}
