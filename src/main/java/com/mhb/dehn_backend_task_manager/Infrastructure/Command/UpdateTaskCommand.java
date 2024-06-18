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
            @ShellOption(defaultValue = "0") String taskId,
            @ShellOption(defaultValue = "") String title,
            @ShellOption(defaultValue = "") String description,
            @ShellOption(defaultValue = "") String dueDate,
            @ShellOption(defaultValue = "") String status
    ) {
        try {
            int taskIdInt = Integer.parseInt(taskId);
            if (taskIdInt == 0 || title.equals("") || description.equals("") || dueDate.equals("") || status.equals("")) {
                return "Please provide all the required fields: taskId, title, description, dueDate and status";
            }
            this.updateTask.execute(taskIdInt, title, description, dueDate, status);
            return "Task with id " + taskId + " updated";
        } catch (NumberFormatException e) {
            return "Please provide a valid taskId";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
