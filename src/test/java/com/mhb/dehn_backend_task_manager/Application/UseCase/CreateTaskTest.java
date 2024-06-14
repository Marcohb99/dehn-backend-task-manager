package com.mhb.dehn_backend_task_manager.Application.UseCase;

import com.mhb.dehn_backend_task_manager.Domain.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CreateTaskTest {
    @Test
    public void testCreateTaskHappyPath() {
        // Given
        TaskRepository repositoryMock = Mockito.mock(TaskRepository.class);
        CreateTask sut = new CreateTask(repositoryMock);

        // When
        sut.execute("title", "description", "dueDate");

        // Then
        Mockito.verify(repositoryMock)
                .create("title", "description", "dueDate");
    }
}
