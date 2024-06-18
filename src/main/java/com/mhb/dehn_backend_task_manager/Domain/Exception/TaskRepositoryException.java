package com.mhb.dehn_backend_task_manager.Domain.Exception;

public class TaskRepositoryException extends RuntimeException {
    public TaskRepositoryException(String message) {
        super(message);
    }

    public static TaskRepositoryException fromError(String message) {
        return new TaskRepositoryException(message);
    }
}
