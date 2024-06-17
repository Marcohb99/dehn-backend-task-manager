package com.mhb.dehn_backend_task_manager.Domain.Exception;

public class TaskNotFound extends RuntimeException {
    public TaskNotFound(String message) {
        super(message);
    }

    public static TaskNotFound fromTaskId(Integer id) {
        return new TaskNotFound("Task with id " + id + " not found");
    }
}
