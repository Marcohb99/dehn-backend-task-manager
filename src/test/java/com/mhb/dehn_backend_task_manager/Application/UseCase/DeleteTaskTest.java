package com.mhb.dehn_backend_task_manager.Application.UseCase;

import com.mhb.dehn_backend_task_manager.Domain.Exception.TaskNotFound;
import com.mhb.dehn_backend_task_manager.Domain.Exception.TaskRepositoryException;
import com.mhb.dehn_backend_task_manager.Domain.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeleteTaskTest {
    @Test
    public void testUpdateTask() throws TaskRepositoryException, TaskNotFound {
        // Given / Arrange
        TaskRepository repositoryMock = Mockito.mock(TaskRepository.class);
        DeleteTask sut = new DeleteTask(repositoryMock);

        // When / Act
        sut.execute(1);

        // Then / Assert
        Mockito.verify(repositoryMock, Mockito.times(1)).delete(1);
    }
}
