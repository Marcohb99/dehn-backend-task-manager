package com.mhb.dehn_backend_task_manager.Infrastructure.Persistence.Json;

import com.mhb.dehn_backend_task_manager.Domain.Exception.TaskNotFound;
import com.mhb.dehn_backend_task_manager.Domain.Task;
import com.mhb.dehn_backend_task_manager.Domain.TaskStatus;
import com.mhb.dehn_backend_task_manager.Infrastructure.TaskFixture;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class JsonTaskRepositoryTest {
    private final String databasePath = "src/test/java/com/mhb/dehn_backend_task_manager/Infrastructure/Persistence/Json/test_database.json";

    @Test
    public void testCreate() throws IOException, ParseException {
        // Given / Arrange
        TaskFixture.cleanDatabase(this.databasePath);
        JsonTaskRepository jsonTaskRepository = new JsonTaskRepository(databasePath);
        Task expected = new Task(1, "title", "description", "dueDate", TaskStatus.PENDING);
        String title = "title";
        String description = "description";
        String dueDate = "dueDate";

        // When / Act
        Task task = jsonTaskRepository.create(title, description, dueDate, TaskStatus.PENDING);

        // Then / Assert
        assert expected.equals(task);

        // Clean up
        TaskFixture.cleanDatabase(this.databasePath);
    }

    @Test
    public void testFindAll() throws IOException, ParseException {
        // Given / Arrange
        TaskFixture.cleanDatabase(this.databasePath);
        TaskFixture.insertTasks(this.databasePath);
        JsonTaskRepository jsonTaskRepository = new JsonTaskRepository(databasePath);

        // When / Act
        List<Task> tasks = jsonTaskRepository.findAll();

        // Then / Assert
        assert tasks.equals(
                List.of(
                new Task(1, "Task 1", "Description 1", "2021-01-01", TaskStatus.COMPLETED),
                new Task(2, "Task 2", "Description 2", "2021-01-02", TaskStatus.PENDING),
                new Task(3, "Task 3", "Description 3", "2021-01-03", TaskStatus.PENDING)
            )
        );

        // Clean up
        TaskFixture.cleanDatabase(this.databasePath);
    }

    @Test
    public void testUpdate() throws IOException, ParseException, TaskNotFound {
        // Given / Arrange
        TaskFixture.cleanDatabase(this.databasePath);
        TaskFixture.insertTasks(this.databasePath);
        JsonTaskRepository jsonTaskRepository = new JsonTaskRepository(databasePath);
        Task task = new Task(
                2,
                "Task 2",
                "Description 2",
                "2021-01-02",
                TaskStatus.PENDING
        );
        Task updatedTask = new Task(
                2,
                "Task 22",
                "Description 22",
                "2021-02-02",
                TaskStatus.COMPLETED
        );

        // When / Act
        jsonTaskRepository.update(updatedTask);

        // Then / Assert
        try {
            List<Task> tasks = jsonTaskRepository.findAll();
            assert tasks.equals(
                    List.of(
                            new Task(1, "Task 1", "Description 1", "2021-01-01", TaskStatus.COMPLETED),
                            new Task(2, "Task 22", "Description 22", "2021-02-02", TaskStatus.COMPLETED),
                            new Task(3, "Task 3", "Description 3", "2021-01-03", TaskStatus.PENDING)
                    )
            );
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        // Clean up
        TaskFixture.cleanDatabase(this.databasePath);
    }

    @Test
    public void testDelete() throws IOException, ParseException {
        // Given / Arrange
        TaskFixture.cleanDatabase(this.databasePath);
        TaskFixture.insertTasks(this.databasePath);
        JsonTaskRepository jsonTaskRepository = new JsonTaskRepository(databasePath);

        // When / Act
        jsonTaskRepository.delete(2);

        // Then / Assert
        try {
            List<Task> tasks = jsonTaskRepository.findAll();
            assert tasks.equals(
                    List.of(
                            new Task(1, "Task 1", "Description 1", "2021-01-01", TaskStatus.COMPLETED),
                            new Task(3, "Task 3", "Description 3", "2021-01-03", TaskStatus.PENDING)
                    )
            );
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        // Clean up
        TaskFixture.cleanDatabase(this.databasePath);
    }

    @Test
    public void testDeleteTaskNotFound() {
        // Given / Arrange
        TaskFixture.cleanDatabase(this.databasePath);
        JsonTaskRepository jsonTaskRepository = new JsonTaskRepository(databasePath);

        // When / Act & Then / Assert
        Exception exception = assertThrows(
                TaskNotFound.class,
                () -> jsonTaskRepository.delete(1)
        );
        assert exception.getMessage().equals("Task with id 1 not found");
    }
}
