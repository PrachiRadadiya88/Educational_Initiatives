package com.example.scheduler.factory;

import com.example.scheduler.model.Task;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class TaskFactory {
    // Parse times like "07:00"
    public static Task create(String title, String startStr, String endStr, String priority) {
        LocalTime s = parse(startStr);
        LocalTime e = parse(endStr);
        if (!e.isAfter(s))
            throw new IllegalArgumentException("End time must be after start time.");
        return new Task(title.trim(), s, e, priority.trim());
    }

    private static LocalTime parse(String t) {
        try {
            return LocalTime.parse(t);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Invalid time format: " + t + ". Use HH:mm");
        }
    }
}
