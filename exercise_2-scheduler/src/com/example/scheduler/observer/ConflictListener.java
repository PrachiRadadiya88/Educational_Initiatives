package com.example.scheduler.observer;

public interface ConflictListener {
    void onConflictDetected(String existingTaskTitle, String newTaskTitle);
    void onTaskAdded(String title);
    void onTaskRemoved(String title);
}
