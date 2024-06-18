package com.mhb.dehn_backend_task_manager.Infrastructure.Persistence.Json;

import com.mhb.dehn_backend_task_manager.Domain.Exception.TaskNotFound;
import com.mhb.dehn_backend_task_manager.Domain.Exception.TaskRepositoryException;
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
    ) throws TaskRepositoryException {
        Integer nextId = this.getNextId();
        JSONObject taskObj = new JSONObject();
        taskObj.put("id", nextId);
        taskObj.put("title", title);
        taskObj.put("description", description);
        taskObj.put("due_date", dueDate);
        taskObj.put("status", status.toString());


        this.updateJsonWithNewTask(taskObj, nextId);
        return new Task(nextId, title, description, dueDate, status);
    }

    @Override
    public List<Task> findAll() throws TaskRepositoryException {
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
    public void update(Task task) throws TaskRepositoryException, TaskNotFound {
        JSONObject oldJsonObject = getJsonObject();
        JSONArray taskList = (JSONArray) oldJsonObject.get("tasks");
        boolean taskExists = false;

        for (JSONObject taskJson : (Iterable<JSONObject>) taskList) {
            Integer taskId = ((Long) taskJson.get("id")).intValue();
            if (taskId.equals(task.getId())) {
                taskExists = true;
                taskJson.put("title", task.getTitle());
                taskJson.put("description", task.getDescription());
                taskJson.put("due_date", task.getDueDate());
                taskJson.put("status", task.getStatus().toString());
            }
        }
        if (!taskExists) {
            throw TaskNotFound.fromTaskId(task.getId());
        }

        this.updateJson(oldJsonObject, taskList);
    }

    @Override
    public void delete(Integer taskId) throws TaskRepositoryException, TaskNotFound {
        JSONObject oldJsonObject = getJsonObject();

        JSONArray taskList = (JSONArray) oldJsonObject.get("tasks");
        boolean taskExists = false;

        for (JSONObject taskJson : (Iterable<JSONObject>) taskList) {
            Integer id = ((Long) taskJson.get("id")).intValue();
            if (id.equals(taskId)) {
                taskExists = true;
                taskList.remove(taskJson);
                break;
            }
        }

        if (!taskExists) {
            throw TaskNotFound.fromTaskId(taskId);
        }

        this.updateJson(oldJsonObject, taskList);
    }

    private void updateJson(JSONObject oldJsonObject, JSONArray taskList) {
        JSONObject newJson = new JSONObject();
        newJson.put("next_id", oldJsonObject.get("next_id"));
        newJson.put("tasks", taskList);

        try (FileWriter file = new FileWriter(this.databasePath)) {
            file.write(newJson.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateJsonWithNewTask(JSONObject taskObj, Integer nextId) throws TaskRepositoryException {
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
            throw TaskRepositoryException.fromError(e.getMessage());
        }
    }

    private Integer getNextId() throws TaskRepositoryException {
        return ((Long) getJsonObject().get("next_id")).intValue();
    }

    private JSONObject getJsonObject() throws TaskRepositoryException {
        try {
            JSONParser parser = new JSONParser();
            Reader reader = new FileReader(this.databasePath);
            Object oldJson = parser.parse(reader);
            return (JSONObject) oldJson;
        } catch (IOException | ParseException e) {
            throw TaskRepositoryException.fromError(e.getMessage());
        }
    }
}
