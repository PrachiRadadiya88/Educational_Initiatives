package com.example.scheduler.observer;

import java.util.logging.Logger;

public class ConsoleConflictListener implements ConflictListener {
    private static final Logger logger = Logger.getLogger(ConsoleConflictListener.class.getName());

    @Override
    public void onConflictDetected(String existing, String incoming){
        logger.warning("Conflict: task \""+incoming+"\" conflicts with existing \""+existing+"\"");
    }
    @Override
    public void onTaskAdded(String title){
        logger.info("Task added: " + title);
    }
    @Override
    public void onTaskRemoved(String title){
        logger.info("Task removed: " + title);
    }
}
