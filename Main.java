import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- To-Do List Menu ---");
            System.out.println("1. Add Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter due date (YYYY-MM-DD): ");
                    String dueDate = scanner.nextLine();
                    TaskManager.addTask(title, category, dueDate);
                    break;

                case 2:
                System.out.printf("%-5s %-30s %-20s %-15s %-10s%n", "ID", "Title", "Category", "Due Date", "Status");
                System.out.println("------------------------------------------------------------");
                for (Task task : TaskManager.getAllTasks()) {
                    System.out.printf("%-5d %-30s %-20s %-15s %-10s%n",
                            task.getId(),
                            task.getTitle(),
                            task.getCategory(),
                            task.getDueDate(),
                            task.isCompleted() ? "Completed" : "Pending");
                }
                break;

                case 3:
                    System.out.print("Enter task ID to mark as completed: ");
                    int taskIdComplete = scanner.nextInt();
                    TaskManager.markTaskAsCompleted(taskIdComplete);
                    break;
                
                case 4:
                    System.out.print("Enter task ID to delete: ");
                    int taskIdDelete = scanner.nextInt();
                    TaskManager.deleteTask(taskIdDelete);
                    break;
                
                case 5:
                    System.out.println("Exiting the application. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
        
    }
}
