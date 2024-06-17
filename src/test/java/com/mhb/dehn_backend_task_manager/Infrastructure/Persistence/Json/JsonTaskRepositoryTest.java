package com.mhb.dehn_backend_task_manager.Infrastructure.Persistence.Json;

import com.mhb.dehn_backend_task_manager.Domain.Task;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.io.FileWriter;
import java.io.IOException;

@SpringBootTest
public class JsonTaskRepositoryTest {
    private final String databasePath = "src/test/java/com/mhb/dehn_backend_task_manager/Infrastructure/Persistence/Json/test_database.json";

    @Test
    public void testCreate() throws IOException, ParseException {
        // Given / Arrange
        this.cleanDatabase();
        JsonTaskRepository jsonTaskRepository = new JsonTaskRepository(databasePath);
        Task expected = new Task(1, "title", "description", "dueDate");
        String title = "title";
        String description = "description";
        String dueDate = "dueDate";

        // When / Act
        Task task = jsonTaskRepository.create(title, description, dueDate);

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
}
