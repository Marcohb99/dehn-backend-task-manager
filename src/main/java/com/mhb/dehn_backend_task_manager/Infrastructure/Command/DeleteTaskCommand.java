package com.mhb.dehn_backend_task_manager.Infrastructure.Command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class DeleteTaskCommand {
    @ShellMethod(key = "delete-task", value = "Delete a task with a given id")
    public String invoke(Integer taskId) {
        return "Task with id " + taskId + " deleted";
    }
}
