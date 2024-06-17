package com.mhb.dehn_backend_task_manager.Infrastructure.Command;

import com.mhb.dehn_backend_task_manager.Domain.Task;
import com.mhb.dehn_backend_task_manager.Domain.TaskStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ListTasksCommandTest {
    @Test
    public void testInvoke() {
        // Given / Arrange
        ListTasksCommand listTasksCommand = new ListTasksCommand();
        List<Task> tasks = List.of(
                new Task(1, "Task 1", "Description 1", "2021-01-01", TaskStatus.COMPLETED),
                new Task(2, "Task 2", "Description 2", "2021-01-02", TaskStatus.PENDING),
                new Task(3, "Task 3", "Description 3", "2021-01-03", TaskStatus.PENDING)
        );
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
        String expected = output.toString();

        // When / Act
        String result = listTasksCommand.invoke();

        // Then / Assert
        assert result.equals(expected);
    }
}
