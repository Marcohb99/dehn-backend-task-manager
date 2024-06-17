package com.mhb.dehn_backend_task_manager.Infrastructure.Persistence.Json;

import com.mhb.dehn_backend_task_manager.Domain.Task;
import com.mhb.dehn_backend_task_manager.Domain.TaskRepository;
import com.mhb.dehn_backend_task_manager.Domain.TaskStatus;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.json.simple.parser.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JsonTaskRepository implements TaskRepository {
    private final String databasePath;

    public JsonTaskRepository(
            @Value("${database.path}") String databasePath
    ) {
        this.databasePath = databasePath;
    }

    @Override
    public Task create(
            String title,
            String description,
            String dueDate,
            TaskStatus status
    ) throws IOException, ParseException {
        Integer nextId = this.getNextId();
        JSONObject taskObj = new JSONObject();
        taskObj.put("id", nextId);
        taskObj.put("title", title);
        taskObj.put("description", description);
        taskObj.put("due_date", dueDate);
        taskObj.put("status", status.toString());


        try {
            this.updateJsonFile(taskObj, nextId);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print("Inserted " + taskObj.toJSONString());
        return new Task(nextId, title, description, dueDate, status);
    }

    @Override
    public List<Task> findAll() throws IOException, ParseException {
        List<Task> result = new ArrayList<>();
        JSONObject jsonObject = getJsonObject();
        JSONArray tasks = (JSONArray) jsonObject.get("tasks");
        for (JSONObject taskJson : (Iterable<JSONObject>) tasks) {
            Task task = new Task(
                    ((Long) taskJson.get("id")).intValue(),
                    (String) taskJson.get("title"),
                    (String) taskJson.get("description"),
                    (String) taskJson.get("due_date"),
                    TaskStatus.fromString((String) taskJson.get("status"))
            );
            result.add(task);
        }
        return result;
    }

    @Override
    public void update(Task task) {
        // TODO: Implement this method
    }

    private void updateJsonFile(JSONObject taskObj, Integer nextId) throws IOException, ParseException {
        JSONObject oldJsonObject = getJsonObject();

        JSONArray taskList = (JSONArray) oldJsonObject.get("tasks");
        JSONObject newJson = new JSONObject();

        newJson.put("next_id", nextId + 1); // Increment next_id
        taskList.add(taskObj);
        newJson.put("tasks", taskList);

        try (FileWriter file = new FileWriter(this.databasePath)) {
            file.write(newJson.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Integer getNextId() throws IOException, ParseException {
        return ((Long) getJsonObject().get("next_id")).intValue();
    }

    private JSONObject getJsonObject() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Reader reader = new FileReader(this.databasePath);
        Object oldJson = parser.parse(reader);
        return (JSONObject) oldJson;
    }
}
