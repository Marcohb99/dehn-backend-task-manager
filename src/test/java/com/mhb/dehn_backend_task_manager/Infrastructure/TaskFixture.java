package com.mhb.dehn_backend_task_manager.Infrastructure;

import com.mhb.dehn_backend_task_manager.Domain.Task;
import com.mhb.dehn_backend_task_manager.Domain.TaskStatus;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TaskFixture {
    public static void cleanDatabase(String databasePath) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("next_id", 1);
        jsonObject.put("tasks", new JSONArray());
        try (FileWriter file = new FileWriter(databasePath)) {
            file.write(jsonObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void insertTasks(String databasePath) {
        List<Task> tasks = List.of(
                new Task(1, "Task 1", "Description 1", "2021-01-01", TaskStatus.COMPLETED),
                new Task(2, "Task 2", "Description 2", "2021-01-02", TaskStatus.PENDING),
                new Task(3, "Task 3", "Description 3", "2021-01-03", TaskStatus.PENDING)
        );

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("next_id", 4);
        JSONArray taskList = new JSONArray();
        for (Task task: tasks) {
            JSONObject taskObj = new JSONObject();
            taskObj.put("id", task.getId());
            taskObj.put("title", task.getTitle());
            taskObj.put("description", task.getDescription());
            taskObj.put("due_date", task.getDueDate());
            taskObj.put("status", task.getStatus().toString());
            taskList.add(taskObj);
        }
        jsonObject.put("tasks", taskList);

        try (FileWriter file = new FileWriter(databasePath)) {
            file.write(jsonObject.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
