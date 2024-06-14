package com.mhb.dehn_backend_task_manager.Infrastructure.Command;

import com.mhb.dehn_backend_task_manager.Application.UseCase.CreateTask;
import com.mhb.dehn_backend_task_manager.Domain.TaskRepository;
import com.mhb.dehn_backend_task_manager.Infrastructure.Persistence.Json.JsonTaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CreateTaskCommandTest {
    //TODO: use the shell test framework to test the command
    // https://docs.spring.io/spring-shell/reference/testing/basics.html
    @Test
    public void testInvoke() {
        // Given
        CreateTaskCommand createTaskCommand = new CreateTaskCommand(
                new CreateTask(
                        new JsonTaskRepository()
                )
        );

        // When
        String result = createTaskCommand.invoke(
                "sample-title",
                "sample-description",
                "sample-dueDate"
        );

        // Then
        assert result.equals(
                String.format(
                        "Task with id: %d, title: %s, and due date: %s created successfully!",
                        1,
                        "sample-title",
                        "sample-dueDate"
                )
        );
    }
}
