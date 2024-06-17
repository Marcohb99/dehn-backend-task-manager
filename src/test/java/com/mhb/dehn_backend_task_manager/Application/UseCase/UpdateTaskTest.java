package com.mhb.dehn_backend_task_manager.Application.UseCase;

import com.mhb.dehn_backend_task_manager.Domain.Task;
import com.mhb.dehn_backend_task_manager.Domain.TaskRepository;
import com.mhb.dehn_backend_task_manager.Domain.TaskStatus;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UpdateTaskTest {
    @Test
    public void testExecute() {
        // Given / Arrange
        TaskRepository repositoryMock = Mockito.mock(TaskRepository.class);
        UpdateTask sut = new UpdateTask(repositoryMock);

        // When / Act
        sut.execute(1, "title", "description", "2021-01-01", "PENDING");

        // Then / Assert
        Mockito.verify(repositoryMock, Mockito.times(1)).update(
                new Task(
                        1,
                        "title",
                        "description",
                        "2021-01-01",
                        TaskStatus.PENDING
                )
        );
    }
}
