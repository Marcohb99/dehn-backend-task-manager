package com.mhb.dehn_backend_task_manager.Infrastructure.Persistence.Json;

import com.mhb.dehn_backend_task_manager.Domain.TaskRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JsonTaskRepository implements TaskRepository {
    @Override
    public void create(String title, String description, String dueDate) {
        // TODO: Implement this method
    }
}
