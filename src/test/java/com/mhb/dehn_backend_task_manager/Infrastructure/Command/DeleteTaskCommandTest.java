package com.mhb.dehn_backend_task_manager.Infrastructure.Command;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeleteTaskCommandTest {
    @Test
    public void testInvoke() {
        // Given / Arrange
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand();

        // When / Act
        String result = deleteTaskCommand.invoke(1);

        // Then / Assert
        assert "Task with id 1 deleted".equals(result);
    }
}
