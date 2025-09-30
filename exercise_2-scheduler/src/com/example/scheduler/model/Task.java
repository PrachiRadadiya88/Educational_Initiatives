package com.example.scheduler.model;

import java.time.LocalTime;
import java.util.Objects;

public class Task {
    private final String title;
    private LocalTime start;
    private LocalTime end;
    private final String priority;
    private boolean completed = false;

    public Task(String title, LocalTime start, LocalTime end, String priority) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public String getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean c) {
        this.completed = c;
    }

    public void setStart(LocalTime s) {
        this.start = s;
    }

    public void setEnd(LocalTime e) {
        this.end = e;
    }

    public boolean overlaps(Task other) {
        return !(end.isBefore(other.start) || end.equals(other.start) || start.isAfter(other.end)
                || start.equals(other.end));
    }

    @Override
    public String toString() {
        return String.format("%s: %s - %s [%s]%s",
                title, start.toString(), end.toString(), priority, completed ? " (Done)" : "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Task))
            return false;
        Task t = (Task) o;
        return Objects.equals(title, t.title) && Objects.equals(start, t.start) && Objects.equals(end, t.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, start, end);
    }
}
