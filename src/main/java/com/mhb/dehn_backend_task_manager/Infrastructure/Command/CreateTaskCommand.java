package com.mhb.dehn_backend_task_manager.Infrastructure.Command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class CreateTaskCommand {
    @ShellMethod(key = "get-tasks", value = "Get all tasks")
    public String invoke(
            @ShellOption String title,
            @ShellOption String description,
            @ShellOption String dueDate
    ) {
        return String.format(
                "Task with id: %d, title: %s, and due date: %s created successfully!",
                1,
                title,
                dueDate
        );
    }
}
