package com.mhb.dehn_backend_task_manager.Application.UseCase;

import com.mhb.dehn_backend_task_manager.Domain.Task;
import com.mhb.dehn_backend_task_manager.Domain.TaskRepository;
import com.mhb.dehn_backend_task_manager.Domain.TaskStatus;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
public class ListTasksTest {
    @Test
    public void testExecute() throws IOException, ParseException {
        // Given / Arrange
        TaskRepository repositoryMock = Mockito.mock(TaskRepository.class);
        Mockito.when(repositoryMock.findAll()).thenReturn(
                List.of(
                        new Task(1, "Task 1", "Description 1", "2021-01-01", TaskStatus.COMPLETED),
                        new Task(2, "Task 2", "Description 2", "2021-01-02", TaskStatus.PENDING),
                        new Task(3, "Task 3", "Description 3", "2021-01-03", TaskStatus.PENDING)
                )
        );
        ListTasks sut = new ListTasks(repositoryMock);

        // When / Act
        List<Task> result = sut.execute();

        // Then / Assert
        assert result.equals(
                List.of(
                        new Task(1, "Task 1", "Description 1", "2021-01-01", TaskStatus.COMPLETED),
                        new Task(2, "Task 2", "Description 2", "2021-01-02", TaskStatus.PENDING),
                        new Task(3, "Task 3", "Description 3", "2021-01-03", TaskStatus.PENDING)
                )
        );
        Mockito.verify(repositoryMock, Mockito.times(1)).findAll();
    }
}
