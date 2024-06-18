package com.mhb.dehn_backend_task_manager.Infrastructure.Command;

import com.mhb.dehn_backend_task_manager.Application.UseCase.DeleteTask;
import com.mhb.dehn_backend_task_manager.Infrastructure.Persistence.Json.JsonTaskRepository;
import com.mhb.dehn_backend_task_manager.Infrastructure.TaskFixture;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "database.path=src/test/java/com/mhb/dehn_backend_task_manager/Infrastructure/Persistence/Json/test_database.json")
public class DeleteTaskCommandTest {
    private final String databasePath = "src/test/java/com/mhb/dehn_backend_task_manager/Infrastructure/Persistence/Json/test_database.json";
    @Test
    public void testInvoke() {
        // Given / Arrange
        TaskFixture.cleanDatabase(databasePath);
        TaskFixture.insertTasks(databasePath);
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(
                new DeleteTask(
                        new JsonTaskRepository(databasePath)
                )
        );

        // When / Act
        String result = deleteTaskCommand.invoke("1");

        // Then / Assert
        assert "Task with id 1 deleted".equals(result);

        // Clean up
        TaskFixture.cleanDatabase(databasePath);
    }

    @Test
    public void testInvokeTaskNotFound() {
        // Given / Arrange
        TaskFixture.cleanDatabase(databasePath);
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(
                new DeleteTask(
                        new JsonTaskRepository(databasePath)
                )
        );

        // When / Act
        String result = deleteTaskCommand.invoke("1");

        // Then / Assert
        assert "Task with id 1 not found".equals(result);
    }

    @Test
    public void testInvokeInvalidTaskId() {
        // Given / Arrange
        TaskFixture.cleanDatabase(databasePath);
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(
                new DeleteTask(
                        new JsonTaskRepository(databasePath)
                )
        );

        // When / Act
        String result = deleteTaskCommand.invoke("abc");

        // Then / Assert
        assert "Please provide a valid task id to delete".equals(result);
    }

    @Test
    public void testInvokeInvalidTaskIdZero() {
        // Given / Arrange
        TaskFixture.cleanDatabase(databasePath);
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(
                new DeleteTask(
                        new JsonTaskRepository(databasePath)
                )
        );

        // When / Act
        String result = deleteTaskCommand.invoke("0");

        // Then / Assert
        assert "Please provide a task id to delete".equals(result);
    }
}
