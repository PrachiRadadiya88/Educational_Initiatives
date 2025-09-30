package com.example.scheduler.manager;

import com.example.scheduler.model.Task;
import com.example.scheduler.observer.ConflictListener;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ScheduleManager {
    private static final Logger logger = Logger.getLogger(ScheduleManager.class.getName());
    // eager singleton
    private static final ScheduleManager INSTANCE = new ScheduleManager();
    private final List<Task> tasks = new ArrayList<>();
    private final List<ConflictListener> listeners = new ArrayList<>();

    private ScheduleManager() {
    }

    public static ScheduleManager getInstance() {
        return INSTANCE;
    }

    public synchronized void registerListener(ConflictListener l) {
        listeners.add(l);
    }

    public synchronized void unregisterListener(ConflictListener l) {
        listeners.remove(l);
    }

    public synchronized void addTask(Task t) {
        Optional<Task> conflict = tasks.stream().filter(existing -> existing.overlaps(t)).findFirst();
        if (conflict.isPresent()) {
            listeners.forEach(l -> l.onConflictDetected(conflict.get().getTitle(), t.getTitle()));
            throw new IllegalStateException("Task conflicts with existing task: " + conflict.get().getTitle());
        }
        tasks.add(t);
        tasks.sort(Comparator.comparing(Task::getStart));
        listeners.forEach(l -> l.onTaskAdded(t.getTitle()));
        logger.info("Added " + t);
    }

    public synchronized void removeTask(String title) {
        Optional<Task> found = tasks.stream().filter(tt -> tt.getTitle().equalsIgnoreCase(title)).findFirst();
        if (!found.isPresent()) {
            throw new NoSuchElementException("Task not found: " + title);
        }
        tasks.remove(found.get());
        listeners.forEach(l -> l.onTaskRemoved(title));
        logger.info("Removed task " + title);
    }

    public synchronized List<Task> viewTasks() {
        return tasks.stream().collect(Collectors.toList());
    }

    public synchronized Optional<Task> find(String title) {
        return tasks.stream().filter(t -> t.getTitle().equalsIgnoreCase(title)).findFirst();
    }

    public synchronized void markCompleted(String title) {
        Task t = find(title).orElseThrow(() -> new NoSuchElementException("Task not found: " + title));
        t.setCompleted(true);
        logger.info("Marked completed: " + title);
    }

    public synchronized List<Task> viewByPriority(String priority) {
        return tasks.stream().filter(t -> t.getPriority().equalsIgnoreCase(priority)).collect(Collectors.toList());
    }
}
