import java.util.List;

public class Main {
  public static void main(String[] args) {
    TaskManager taskManager = new TaskManager();

    // Add a new task
    taskManager.addTask("Task 1");

    // Edit the description of task with ID 1
    taskManager.editTask(1, "Updated Task 1");

    // Delete the task with ID 1
    taskManager.deleteTask(1);

    // Get all tasks and print their descriptions
    List<Task> tasks = taskManager.getAllTasks();

    for (Task task : tasks) {
      System.out.println(task.getDescription());
    }
  }
}
