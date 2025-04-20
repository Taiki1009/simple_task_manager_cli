package com.example.core;

import com.example.storage.TaskStorage;
import java.util.List;

public class TaskManager {
  private final List<Task> tasks;
  private final TaskStorage storage;

  public TaskManager() {
    storage = new TaskStorage();
    tasks = storage.loadTasks();
  }

  public void addTask(Task task) {
    tasks.add(task);
    storage.saveTasks(tasks);
  }

  public List<Task> getTasks() {
    return tasks;
  }
}