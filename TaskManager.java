import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
  private List<Task> taskList;
  private final String FILE_PATH = "tasks.ser";

  public TaskManager() {
    taskList = loadTasksFromFile();
  }

  public List<Task> getAllTasks() {
    return taskList;
  }

  public void addTask(String description) {
    int id = taskList.size() + 1;
    Task task = new Task(id, description);
    taskList.add(task);
    saveTasksToFile();
  }

  public void editTask(int taskId, String newDescription) {
    for (Task task : taskList) {
      if (task.getId() == taskId) {
        task.setDescription(newDescription);
        break;
      }
    }
    saveTasksToFile();
  }

  public void deleteTask(int taskId) {
    Task taskToRemove = null;
    for (Task task : taskList) {
      if (task.getId() == taskId) {
        taskToRemove = task;
        break;
      }
    }
    if (taskToRemove != null) {
      taskList.remove(taskToRemove);
      saveTasksToFile();
    }
  }

  private void saveTasksToFile() {
    try (FileOutputStream fileOut = new FileOutputStream(FILE_PATH);
         ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
      objectOut.writeObject(taskList);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private List<Task> loadTasksFromFile() {
    List<Task> tasks = new ArrayList<>();
    try (FileInputStream fileIn = new FileInputStream(FILE_PATH);
         ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
      tasks = (List<Task>) objectIn.readObject();
    } catch (FileNotFoundException e) {
      // This will be thrown when the file doesn't exist yet
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return tasks;
  }
}
