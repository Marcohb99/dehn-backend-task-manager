package com.mhb.dehn_backend_task_manager.Infrastructure.Command;

import com.mhb.dehn_backend_task_manager.Application.UseCase.UpdateTask;
import com.mhb.dehn_backend_task_manager.Domain.TaskStatus;
import com.mhb.dehn_backend_task_manager.Infrastructure.Persistence.Json.JsonTaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "database.path=src/test/java/com/mhb/dehn_backend_task_manager/Infrastructure/Persistence/Json/test_database.json")
public class UpdateTaskCommandTest {
    private final String databasePath = "src/test/java/com/mhb/dehn_backend_task_manager/Infrastructure/Persistence/Json/test_database.json";
    @Test
    public void testInvoke() {
        // Given /Arrange
        UpdateTaskCommand updateTaskCommand = new UpdateTaskCommand(
                new UpdateTask(
                        new JsonTaskRepository(databasePath)
                )
        );

        // When / Act
        String result = updateTaskCommand.invoke(
                1,
                "Task 1",
                "Description of Task 1",
                "2021-12-31",
                TaskStatus.PENDING.toString()
        );

        // Then / Assert
        assert "Task with id 1 updated".equals(result);
    }
}
