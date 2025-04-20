package com.example.cli;

import com.example.core.Task;
import com.example.core.TaskManager;
import java.time.LocalDate;
import java.util.Scanner;

public class TaskManagerCLI {
  private final TaskManager taskManager;
  private final Scanner scanner;

  public TaskManagerCLI() {
    taskManager = new TaskManager();
    scanner = new Scanner(System.in);
  }

  public void run() {
    try (scanner) {
      System.out.println("Task Manager CLI - Welcome!");
      boolean running = true;
      
      while (running) {
        System.out.println("Enter command (add/list/complete/notify/exit):");
        String command = scanner.nextLine();
        
        switch (command.trim().toLowerCase()) {
          case "add" -> addTask();
          case "list" -> listTasks();
          case "complete" -> completeTask();
          case "notify" -> notifyWeekly();
          case "exit" -> running = false;
          default -> System.out.println("Unknown command.");
        }
      }
    }
    System.out.println("Exiting Task Manager CLI.");
  }

  private void addTask() {
    try {
      System.out.println("Enter task title:");
      String title = scanner.nextLine();

      System.out.println("Enter priority(number):");
      int priority = Integer.parseInt(scanner.nextLine());

      System.out.println("Enter importance(number):");
      int importance = Integer.parseInt(scanner.nextLine());

      System.out.println("Enter deadline(YYYY-MM-DD):");
      LocalDate deadline = LocalDate.parse(scanner.nextLine());

      Task task = new Task(title, priority, importance, deadline);
      taskManager.addTask(task);
      System.out.println("Task added: "+ task);
    } catch (NumberFormatException e) {
      System.out.println("Error adding task: " + e.getMessage());
    }
  }

  private void listTasks() {
    System.out.println("Listing tasks:");
    for (Task task : taskManager.getTasks()) {
      System.out.println(task);
    }
  }

  private void completeTask() {
    listTasks();
    System.out.println("Enter the index of the task to mark as completed:");
    int index = Integer.parseInt(scanner.nextLine());
    boolean success = taskManager.completeTask(index);
    if (success) {
        System.out.println("Task marked as completed.");
    } else {
        System.out.println("Invalid index.");
    }
  }

  private void notifyWeekly() {
    int completedCount = taskManager.getWeeklyCompletedCount();
    System.out.println("Weekly Notification: " + completedCount + " tasks were completed in the last week.");
  }

  public static void main(String[] args) {
    new TaskManagerCLI().run();
  }
}