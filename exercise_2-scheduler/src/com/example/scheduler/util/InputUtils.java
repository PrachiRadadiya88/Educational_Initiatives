package com.example.scheduler.util;

import java.util.Scanner;
import java.util.function.Predicate;

public class InputUtils {
    public static String prompt(Scanner sc, String message){
        System.out.print(message + " > ");
        String line = sc.nextLine();
        return line == null ? "" : line.trim();
    }
    public static boolean confirm(Scanner sc, String message){
        System.out.print(message + " (y/n) > ");
        String l = sc.nextLine();
        return l != null && l.trim().equalsIgnoreCase("y");
    }
    public static void showMenu(){
        System.out.println("\nCommands:");
        System.out.println(" add        - Add a task");
        System.out.println(" view       - View all tasks");
        System.out.println(" remove     - Remove a task by title");
        System.out.println(" edit       - Edit a task (optional)");
        System.out.println(" mark       - Mark task completed");
        System.out.println(" byprio     - View tasks by priority");
        System.out.println(" help       - show this menu");
        System.out.println(" exit       - quit");
    }
}
