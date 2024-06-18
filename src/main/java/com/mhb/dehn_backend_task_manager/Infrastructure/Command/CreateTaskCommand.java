package com.mhb.dehn_backend_task_manager.Infrastructure.Command;

import com.mhb.dehn_backend_task_manager.Application.UseCase.CreateTask;
import com.mhb.dehn_backend_task_manager.Domain.Task;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@AllArgsConstructor
public class CreateTaskCommand {
    private final CreateTask createTask;

    @ShellMethod(key = "create-task", value = "Create a task")
    public String invoke(
            @ShellOption(defaultValue = "") String title,
            @ShellOption(defaultValue = "") String description,
            @ShellOption(defaultValue = "") String dueDate,
            @ShellOption(defaultValue = "") String status
    ) {
        if (status.equals("")) {
            status = "pending";
        }
        if (title.equals("") || description.equals("") || dueDate.equals("")) {
            return "Please provide all the required fields: title, description and dueDate";
        }

        try {
            Task task = this.createTask.execute(title, description, dueDate, status);
            return String.format(
                    "Task with id: %d, title: %s, and due date: %s created successfully!",
                    task.getId(),
                    task.getTitle(),
                    task.getDueDate()
            );
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
