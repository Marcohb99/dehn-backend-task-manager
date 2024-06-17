package com.mhb.dehn_backend_task_manager.Infrastructure.Command;

import com.mhb.dehn_backend_task_manager.Application.UseCase.UpdateTask;
import com.mhb.dehn_backend_task_manager.Domain.TaskStatus;
import com.mhb.dehn_backend_task_manager.Infrastructure.Persistence.Json.JsonTaskRepository;
import com.mhb.dehn_backend_task_manager.Infrastructure.TaskFixture;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "database.path=src/test/java/com/mhb/dehn_backend_task_manager/Infrastructure/Persistence/Json/test_database.json")
public class UpdateTaskCommandTest {
    private final String databasePath = "src/test/java/com/mhb/dehn_backend_task_manager/Infrastructure/Persistence/Json/test_database.json";
    @Test
    public void testInvoke() {
        // Given / Arrange
        TaskFixture.cleanDatabase(databasePath);
        TaskFixture.insertTasks(databasePath);
        UpdateTaskCommand updateTaskCommand = new UpdateTaskCommand(
                new UpdateTask(
                        new JsonTaskRepository(databasePath)
                )
        );

        // When / Act
        String result = updateTaskCommand.invoke(
                1,
                "Task 1111",
                "Description of Task 1111",
                "2021-01-01",
                TaskStatus.PENDING.toString()
        );

        // Then / Assert
        assert "Task with id 1 updated".equals(result);

        // Clean up
        TaskFixture.cleanDatabase(databasePath);
    }

    @Test
    public void testInvokeNotFound() {
        // Given / Arrange
        UpdateTaskCommand updateTaskCommand = new UpdateTaskCommand(
                new UpdateTask(
                        new JsonTaskRepository(databasePath)
                )
        );

        // When / Act
        String result = updateTaskCommand.invoke(
                123456,
                "Task 1111",
                "Description of Task 1111",
                "2021-01-01",
                TaskStatus.PENDING.toString()
        );

        // Then / Assert
        assert "Task with id 123456 not found".equals(result);
    }
}
