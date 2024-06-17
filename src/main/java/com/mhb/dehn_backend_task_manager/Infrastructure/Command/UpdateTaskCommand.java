package com.mhb.dehn_backend_task_manager.Infrastructure.Command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class UpdateTaskCommand {

    @ShellMethod(key = "update-task", value = "Update a task with a given id")
    public String invoke(@ShellOption Integer taskId) {
        return "Task with id " + taskId + " updated";
    }
}
