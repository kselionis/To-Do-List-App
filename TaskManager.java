import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    public static void addTask(String title, String category, String dueDate) {
        String sql = "INSERT INTO tasks (title, category, due_date, completed) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setString(2, category);
            stmt.setString(3, dueDate);
            stmt.setBoolean(4, false);
            stmt.executeUpdate();
            System.out.println("Task added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding task: " + e.getMessage());
        }
    }

    public static List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Task task = new Task(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("category"),
                    rs.getString("due_date"),
                    rs.getBoolean("completed")
                );
                tasks.add(task);
            }
        }catch (SQLException e) {
            System.out.println("Error retrieving tasks: " + e.getMessage());
        }
        return tasks;
    }

    public static void markTaskAsCompleted(int taskId) {
        String sql = "UPDATE tasks SET completed = true WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, taskId);
            stmt.executeUpdate();
            System.out.println("Task marked as completed!");
        } catch (SQLException e) {
            System.err.println("Error updating task: " + e.getMessage());
        }
    }

    public static void deleteTask(int taskId) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, taskId);
            stmt.executeUpdate();
            System.out.println("Task deleted successfully!");
        } catch (SQLException e) {
            System.err.println("Error deleting task: " + e.getMessage());
        }
    }
}
