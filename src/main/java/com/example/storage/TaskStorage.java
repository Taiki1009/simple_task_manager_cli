package com.example.storage;

import com.example.core.Task;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskStorage {
  private static final String FILE_NAME = "data/tasks.txt";
  private static final String DEIMITER = "\\|"; // 正規表現: 区切り文字として"|"を使用する

  public void saveTasks(List<Task> tasks) {
    try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
      for (Task task : tasks) {
        // フィールド: title|priority|importance|deadline|copleted
        writer.println(task.getTaskInfo());
      }
    } catch (IOException e) {
      System.out.println("Error saving tasks: " + e.getMessage());
    }
  }

  public List<Task> loadTasks() {
    List<Task> tasks = new ArrayList<>();
    if (!Files.exists(Paths.get(FILE_NAME))) {
      return tasks; // ファイルが存在しない場合は空のリストを返す
    }
    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(DEIMITER);
        if (parts.length >= 6) {
          String title = parts[0];
          int priority = Integer.parseInt(parts[1]);
          int importance = Integer.parseInt(parts[2]);
          LocalDate deadline = LocalDate.parse(parts[3]);
          boolean completed = Boolean.parseBoolean(parts[4]);

          Task task = new Task(title, priority, importance, deadline);
          task.setCompleted(completed);
          tasks.add(task);
       }
      }
    } catch (IOException e) {
      System.out.println("Error loading tasks: " + e.getMessage());
    }

    return tasks;
  }
}
