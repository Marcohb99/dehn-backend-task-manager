package com.mhb.dehn_backend_task_manager.Infrastructure.Command;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UpdateTaskCommandTest {
    @Test
    public void testInvoke() {
        // Given /Arrange
        UpdateTaskCommand updateTaskCommand = new UpdateTaskCommand();

        // When / Act
        String result = updateTaskCommand.invoke(1);

        // Then / Assert
        assert "Task with id 1 updated".equals(result);
    }
}
