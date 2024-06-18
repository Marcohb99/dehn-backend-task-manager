package com.mhb.dehn_backend_task_manager.Infrastructure.Command;

import com.mhb.dehn_backend_task_manager.Application.UseCase.DeleteTask;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@AllArgsConstructor
public class DeleteTaskCommand {
    private final DeleteTask deleteTask;

    @ShellMethod(key = "delete-task", value = "Delete a task with a given id")
    public String invoke(
            @ShellOption(defaultValue = "0") String taskId
    ) {
        try {
            int taskIdInt = Integer.parseInt(taskId);
            if (taskIdInt == 0) return "Please provide a task id to delete";
            this.deleteTask.execute(taskIdInt);
        } catch (NumberFormatException e) {
            return "Please provide a valid task id to delete";
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Task with id " + taskId + " deleted";
    }
}
