package com.mhb.dehn_backend_task_manager.Domain;

public enum TaskStatus {
    PENDING,
    COMPLETED;

    public static TaskStatus fromString(String status) {
        return TaskStatus.valueOf(status.toUpperCase());
    }
}
