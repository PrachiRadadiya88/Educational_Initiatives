package com.example.scheduler;

import com.example.scheduler.factory.TaskFactory;
import com.example.scheduler.manager.ScheduleManager;
import com.example.scheduler.observer.ConsoleConflictListener;
import com.example.scheduler.model.Task;
import com.example.scheduler.util.InputUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ScheduleManager manager = ScheduleManager.getInstance();
        manager.registerListener(new ConsoleConflictListener());

        System.out.println("Astronaut Daily Schedule Organizer");
        InputUtils.showMenu();

        String cmd = "";
        while(!"exit".equalsIgnoreCase(cmd)){
            cmd = InputUtils.prompt(sc, "\nEnter command (help to list)");
            try {
                switch(cmd.toLowerCase()){
                    case "help":
                        InputUtils.showMenu();
                        break;
                    case "add":
                        handleAdd(sc, manager);
                        break;
                    case "view":
                        handleView(manager);
                        break;
                    case "remove":
                        handleRemove(sc, manager);
                        break;
                    case "edit":
                        handleEdit(sc, manager);
                        break;
                    case "mark":
                        handleMark(sc, manager);
                        break;
                    case "byprio":
                        handleByPriority(sc, manager);
                        break;
                    case "exit":
                        System.out.println("Exiting...");
                        break;
                    case "":
                        break;
                    default:
                        System.out.println("Unknown command. Type 'help'.");
                }
            } catch (IllegalArgumentException | IllegalStateException | NoSuchElementException ex){
                System.out.println("Error: " + ex.getMessage());
                logger.warning(ex.getMessage());
            } catch (Exception ex){
                System.out.println("Unexpected error: " + ex.getMessage());
                logger.severe("Unexpected: " + ex.getMessage());
            }
        }
        sc.close();
    }

    private static void handleAdd(Scanner sc, ScheduleManager manager){
        String title = InputUtils.prompt(sc, "Task title");
        String start = InputUtils.prompt(sc, "Start time (HH:mm)");
        String end = InputUtils.prompt(sc, "End time (HH:mm)");
        String priority = InputUtils.prompt(sc, "Priority (High/Medium/Low)");
        Task t = TaskFactory.create(title, start, end, priority);
        manager.addTask(t);
        System.out.println("Task added successfully. No conflicts.");
    }

    private static void handleView(ScheduleManager manager){
        List<Task> tasks = manager.viewTasks();
        if(tasks.isEmpty()){
            System.out.println("No tasks scheduled for the day.");
            return;
        }
        System.out.println("Scheduled tasks:");
        tasks.forEach(t -> System.out.println("  " + t));
    }

    private static void handleRemove(Scanner sc, ScheduleManager manager){
        String title = InputUtils.prompt(sc, "Title to remove");
        manager.removeTask(title);
        System.out.println("Task removed successfully.");
    }

    private static void handleEdit(Scanner sc, ScheduleManager manager){
        String title = InputUtils.prompt(sc, "Title to edit");
        Task t = manager.find(title).orElseThrow(() -> new NoSuchElementException("Task not found"));
        String newStart = InputUtils.prompt(sc, "New start (HH:mm) or press enter to keep " + t.getStart());
        String newEnd = InputUtils.prompt(sc, "New end (HH:mm) or press enter to keep " + t.getEnd());
        if(!newStart.trim().isEmpty() || !newEnd.trim().isEmpty()){
            String s = newStart.trim().isEmpty() ? t.getStart().toString() : newStart;
            String e = newEnd.trim().isEmpty() ? t.getEnd().toString() : newEnd;
            Task temp = TaskFactory.create(t.getTitle(), s, e, t.getPriority());
            manager.removeTask(t.getTitle());
            try {
                manager.addTask(temp);
                System.out.println("Task updated.");
            } catch (Exception ex){
                manager.addTask(t); // restore original
                throw ex;
            }
        } else {
            System.out.println("No changes made.");
        }
    }

    private static void handleMark(Scanner sc, ScheduleManager manager){
        String title = InputUtils.prompt(sc, "Title to mark completed");
        manager.markCompleted(title);
        System.out.println("Marked completed.");
    }

    private static void handleByPriority(Scanner sc, ScheduleManager manager){
        String p = InputUtils.prompt(sc, "Enter priority (High/Medium/Low)");
        List<Task> tasks = manager.viewByPriority(p);
        if(tasks.isEmpty()) System.out.println("No tasks with priority " + p);
        else tasks.forEach(t -> System.out.println("  " + t));
    }
}
