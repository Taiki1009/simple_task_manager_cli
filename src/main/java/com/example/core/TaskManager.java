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

  // 完了処理：インデックスによりタスクを完了状態に更新する
  public boolean completeTask(int index) {
    if (index >= 0 && index < tasks.size()) {
      Task task = tasks.get(index);
      task.setCompleted(true);
      task.setCompletionDate(java.time.LocalDate.now());
      storage.saveTasks(tasks);
      return true;
    }
    return false;
  }

  public List<Task> getTasks() {
    return tasks;
  }

  public int getWeeklyCompletedCount() {
    return (int) tasks.stream()
      .filter(task ->
        task.isCompleted()
        && task.getCompletionDate() != null
        && task.isWithinLastWeek()
      ).count();
  }
}