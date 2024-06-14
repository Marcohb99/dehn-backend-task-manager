package com.mhb.dehn_backend_task_manager.Domain;

public interface TaskRepository {
    public void create(String title, String description, String dueDate);
}
