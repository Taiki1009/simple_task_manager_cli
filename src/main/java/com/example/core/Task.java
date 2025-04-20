package com.example.core;

import java.time.LocalDate;

public class Task {
  private String title;
  private int priority; // 優先度 (1~5)
  private int importance; // 優先度 (1~5)
  private LocalDate deadline;
  private boolean completed;

  public Task(String title, int priority, int importance, LocalDate deadline) {
    this.title = title;
    this.priority = priority;
    this.importance = importance;
    this.deadline = deadline;
    this.completed = false;
  }

  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }

  public int getPriority() { return priority; }
  public void setPriority(int priority) { this.priority = priority; }

  public int getImportance() { return importance; }
  public void setImportance(int importance) { this.importance = importance; }

  public LocalDate getDeadline() { return deadline; }
  public void setDeadline(LocalDate deadline) { this.deadline = deadline; }

  public boolean isCompleted() { return completed; }
  public void setCompleted(boolean completed) { this.completed = completed; }

  public String getTaskInfo() {
    return String.format(
        "%s|%d|%d|%s|%b",
        title,
        priority,
        importance,
        deadline,
        completed
    );
  }

  @Override
  public String toString() {
    return "Task [title=" + title + ", priority=" + priority
      + ", importance=" + importance + ", deadline=" + deadline
      + ", completed=" + completed + "]";
  }
}
