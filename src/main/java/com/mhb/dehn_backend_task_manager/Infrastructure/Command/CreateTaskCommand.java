package com.mhb.dehn_backend_task_manager.Infrastructure.Command;

import com.mhb.dehn_backend_task_manager.Application.UseCase.CreateTask;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@AllArgsConstructor
public class CreateTaskCommand {
    private final CreateTask createTask;

    @ShellMethod(key = "create-task", value = "Create a task")
    public String invoke(
            @ShellOption String title,
            @ShellOption String description,
            @ShellOption String dueDate
    ) {
        this.createTask.execute(title, description, dueDate);
        return String.format(
                "Task with id: %d, title: %s, and due date: %s created successfully!",
                1,
                title,
                dueDate
        );
    }
}
