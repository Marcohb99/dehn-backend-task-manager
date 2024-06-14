package com.mhb.dehn_backend_task_manager.Infrastructure.Command;

import org.junit.jupiter.api.Test;

public class CreateTaskCommandTest {
    @Test
    public void testInvoke() {
        CreateTaskCommand createTaskCommand = new CreateTaskCommand();
        String result = createTaskCommand.invoke(
                "sample-title",
                "sample-description",
                "sample-dueDate"
        );
        assert result.equals("Task created successfully!");
    }
}
