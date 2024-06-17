package com.mhb.dehn_backend_task_manager.Infrastructure.Persistence.Json;

import com.mhb.dehn_backend_task_manager.Domain.Task;
import com.mhb.dehn_backend_task_manager.Domain.TaskStatus;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@SpringBootTest
public class JsonTaskRepositoryTest {
    private final String databasePath = "src/test/java/com/mhb/dehn_backend_task_manager/Infrastructure/Persistence/Json/test_database.json";

    @Test
    public void testCreate() throws IOException, ParseException {
        // Given / Arrange
        this.cleanDatabase();
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
        this.cleanDatabase();
    }

    private void cleanDatabase() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("next_id", 1);
        jsonObject.put("tasks", new JSONArray());
        try (FileWriter file = new FileWriter(this.databasePath)) {
            file.write(jsonObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindAll() {
        // Given / Arrange
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
    }
}
