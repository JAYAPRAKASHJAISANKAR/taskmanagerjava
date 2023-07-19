import java.io.Serializable;

public class Task implements Serializable {
  private int id;
  private String description;

  public Task(int id, String description) {
    this.id = id;
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  // Getters and setters for other properties
}
