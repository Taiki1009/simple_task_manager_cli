package com.example.core;

import java.time.LocalDate;

public class Task {
  private String title;
  private int priority; // 優先度 (1~5)
  private int importance; // 優先度 (1~5)
  private LocalDate deadline;
  private boolean completed;
  private LocalDate completionDate;

  public Task(String title, int priority, int importance, LocalDate deadline) {
    this.title = title;
    this.priority = priority;
    this.importance = importance;
    this.deadline = deadline;
    this.completed = false;
    this.completionDate = null;
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

  public LocalDate getCompletionDate() { return completionDate; }
  public void setCompletionDate(LocalDate completionDate) { this.completionDate = completionDate; }

  public String getTaskInfo() {
    return String.format(
        "%s|%d|%d|%s|%b|%s",
        title,
        priority,
        importance,
        deadline,
        completed,
        completionDate
    );
  }

  /**
   * 指定日が「今日を含めた過去1週間以内」かどうかを返す。
   * ＝ today.minusDays(6) 〜 today の範囲に含まれるかチェック
   */
  public boolean isWithinLastWeek() {
    java.time.LocalDate today = java.time.LocalDate.now();
    java.time.LocalDate weekAgo = today.minusDays(6);
    // [Boolean] weekAgo <= task.completionDate <= today
    return !completionDate.isBefore(weekAgo) && !completionDate.isAfter(today);
  }

  @Override
  public String toString() {
    return "Task [title=" + title + ", priority=" + priority
      + ", importance=" + importance + ", deadline=" + deadline
      + ", completed=" + completed + "]";
  }
}
