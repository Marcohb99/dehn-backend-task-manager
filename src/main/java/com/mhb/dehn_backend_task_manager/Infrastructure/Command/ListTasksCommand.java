package com.mhb.dehn_backend_task_manager.Infrastructure.Command;

import com.mhb.dehn_backend_task_manager.Application.UseCase.ListTasks;
import com.mhb.dehn_backend_task_manager.Domain.Task;
import com.mhb.dehn_backend_task_manager.Domain.TaskStatus;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
@AllArgsConstructor
public class ListTasksCommand {
    private final ListTasks listTasks;

    @ShellMethod(key = "list-tasks", value = "List all tasks")
    public String invoke() {
        List<Task> tasks = this.listTasks.execute();
        StringBuilder output = new StringBuilder("ID\tTitle\tDescription\tDue Date\tStatus\n");
        for (Task task: tasks) {
            output.append(String.format(
                    "%d\t%s\t%s\t%s\t%s\n",
                    task.getId(),
                    task.getTitle(),
                    task.getDescription(),
                    task.getDueDate(),
                    "pending"
            ));
        }
        return output.toString();
    }
}
