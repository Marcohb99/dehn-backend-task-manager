package com.mhb.dehn_backend_task_manager.Infrastructure.Command;

import com.mhb.dehn_backend_task_manager.Application.UseCase.CreateTask;
import com.mhb.dehn_backend_task_manager.Domain.TaskRepository;
import com.mhb.dehn_backend_task_manager.Domain.TaskStatus;
import com.mhb.dehn_backend_task_manager.Infrastructure.Persistence.Json.JsonTaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "database.path=src/test/java/com/mhb/dehn_backend_task_manager/Infrastructure/Persistence/Json/test_database.json")
public class CreateTaskCommandTest {

    private final String databasePath = "src/test/java/com/mhb/dehn_backend_task_manager/Infrastructure/Persistence/Json/test_database.json";
    @Test
    public void testInvoke() {
        // Given / Arrange
        CreateTaskCommand createTaskCommand = new CreateTaskCommand(
                new CreateTask(
                        new JsonTaskRepository(databasePath)
                )
        );

        // When / Act
        String result = createTaskCommand.invoke(
                "sample-title",
                "sample-description",
                "sample-dueDate",
                TaskStatus.PENDING.toString()
        );

        // Then / Assert
        assert result.equals(
                String.format(
                        "Task with id: %d, title: %s, and due date: %s created successfully!",
                        1,
                        "sample-title",
                        "sample-dueDate"
                )
        );
    }

    @Test
    public void testInvokeInvalidParams() {
        // Given / Arrange
        CreateTaskCommand createTaskCommand = new CreateTaskCommand(
                new CreateTask(
                        new JsonTaskRepository(databasePath)
                )
        );

        // When / Act
        String result = createTaskCommand.invoke(
                "",
                "",
                "sample-dueDate",
                TaskStatus.PENDING.toString()
        );

        // Then / Assert
        assert "Please provide all the required fields: title, description and dueDate".equals(result);
    }
}
