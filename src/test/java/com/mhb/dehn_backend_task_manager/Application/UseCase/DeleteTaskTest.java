package com.mhb.dehn_backend_task_manager.Application.UseCase;

import com.mhb.dehn_backend_task_manager.Domain.TaskRepository;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class DeleteTaskTest {
    @Test
    public void testUpdateTask() throws IOException, ParseException {
        // Given / Arrange
        TaskRepository repositoryMock = Mockito.mock(TaskRepository.class);
        DeleteTask sut = new DeleteTask(repositoryMock);

        // When / Act
        sut.execute(1);

        // Then / Assert
        Mockito.verify(repositoryMock, Mockito.times(1)).delete(1);
    }
}
