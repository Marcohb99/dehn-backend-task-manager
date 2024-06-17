package com.mhb.dehn_backend_task_manager.Infrastructure.Command;

import com.mhb.dehn_backend_task_manager.Application.UseCase.UpdateTask;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@AllArgsConstructor
public class UpdateTaskCommand {
    private final UpdateTask updateTask;

    @ShellMethod(key = "update-task", value = "Update a task with a given id")
    public String invoke(
            @ShellOption Integer taskId,
            @ShellOption String title,
            @ShellOption String description,
            @ShellOption String dueDate,
            @ShellOption String status
    ) {
        this.updateTask.execute(taskId, title, description, dueDate, status);
        return "Task with id " + taskId + " updated";
    }
}
