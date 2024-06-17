package com.mhb.dehn_backend_task_manager.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Task {
    private final Integer id;
    private final String title;
    private final String description;
    private final String dueDate;

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Task task = (Task) obj;
        return id.equals(task.id)
                && title.equals(task.title)
                && description.equals(task.description)
                && dueDate.equals(task.dueDate);
    }

    @Override
    public int hashCode() {
        return id.hashCode()
                + title.hashCode()
                + description.hashCode()
                + dueDate.hashCode();
    }
}
