package com.mhb.dehn_backend_task_manager.Infrastructure.Command;

import com.mhb.dehn_backend_task_manager.Application.UseCase.ListTasks;
import com.mhb.dehn_backend_task_manager.Domain.Task;
import com.mhb.dehn_backend_task_manager.Domain.TaskStatus;
import com.mhb.dehn_backend_task_manager.Infrastructure.Persistence.Json.JsonTaskRepository;
import com.mhb.dehn_backend_task_manager.Infrastructure.TaskFixture;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@SpringBootTest
@TestPropertySource(properties = "database.path=src/test/java/com/mhb/dehn_backend_task_manager/Infrastructure/Persistence/Json/test_database.json")
public class ListTasksCommandTest {
    private final String databasePath = "src/test/java/com/mhb/dehn_backend_task_manager/Infrastructure/Persistence/Json/test_database.json";
    @Test
    public void testInvoke() {
        // Given / Arrange
        TaskFixture.cleanDatabase(this.databasePath);
        TaskFixture.insertTasks(this.databasePath);
        ListTasksCommand listTasksCommand = new ListTasksCommand(
                new ListTasks(
                        new JsonTaskRepository(databasePath)
                )
        );
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
                    task.getStatus().toString()
            ));
        }
        String expected = output.toString();

        // When / Act
        String result = listTasksCommand.invoke();

        // Then / Assert
        assert result.equals(expected);

        // Clean up
        TaskFixture.cleanDatabase(this.databasePath);
    }
}
