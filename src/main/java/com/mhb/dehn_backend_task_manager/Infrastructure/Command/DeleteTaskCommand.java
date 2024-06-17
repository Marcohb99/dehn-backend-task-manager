package com.mhb.dehn_backend_task_manager.Infrastructure.Command;

import com.mhb.dehn_backend_task_manager.Application.UseCase.DeleteTask;
import com.mhb.dehn_backend_task_manager.Domain.Exception.TaskNotFound;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@AllArgsConstructor
public class DeleteTaskCommand {
    private final DeleteTask deleteTask;

    @ShellMethod(key = "delete-task", value = "Delete a task with a given id")
    public String invoke(Integer taskId) {
        try {
            this.deleteTask.execute(taskId);
        } catch (TaskNotFound taskNotFound) {
            return taskNotFound.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "Task with id " + taskId + " deleted";
    }
}
