package com.mhb.dehn_backend_task_manager.Application.UseCase;

import com.mhb.dehn_backend_task_manager.Domain.TaskRepository;
import lombok.AllArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class DeleteTask {
    private final TaskRepository taskRepository;

    public void execute(Integer taskId) throws IOException, ParseException {
        this.taskRepository.delete(taskId);
    }
}
