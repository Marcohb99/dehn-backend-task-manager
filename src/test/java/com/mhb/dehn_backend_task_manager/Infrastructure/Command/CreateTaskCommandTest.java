package com.mhb.dehn_backend_task_manager.Infrastructure.Command;

import org.junit.jupiter.api.Test;

public class CreateTaskCommandTest {
    @Test
    public void testInvoke() {
        // Given
        CreateTaskCommand createTaskCommand = new CreateTaskCommand();

        // When
        String result = createTaskCommand.invoke(
                "sample-title",
                "sample-description",
                "sample-dueDate"
        );

        // Then
        assert result.equals(
                String.format(
                        "Task with id: %d, title: %s, and due date: %s created successfully!",
                        1,
                        "sample-title",
                        "sample-dueDate"
                )
        );
    }
}
