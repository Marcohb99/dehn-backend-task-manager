package com.mhb.dehn_backend_task_manager.Application.UseCase;

import com.mhb.dehn_backend_task_manager.Domain.TaskRepository;
import com.mhb.dehn_backend_task_manager.Domain.TaskStatus;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class CreateTaskTest {
    @Test
    public void testCreateTaskHappyPath() throws IOException, ParseException {
        // Given
        TaskRepository repositoryMock = Mockito.mock(TaskRepository.class);
        CreateTask sut = new CreateTask(repositoryMock);

        // When
        sut.execute("title", "description", "dueDate", TaskStatus.COMPLETED.toString());

        // Then
        Mockito.verify(repositoryMock)
                .create("title", "description", "dueDate", TaskStatus.COMPLETED);
    }
}
