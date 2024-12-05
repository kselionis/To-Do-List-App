import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ToDoListAppUI {
    private JFrame frame;
    private JList<String> taskList;
    private DefaultListModel<String> listModel;
    private JTextField titleField;
    private JTextField categoryField;
    private JTextField dueDateField;

    public ToDoListAppUI() {
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("To-Do List App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Task List Panel
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(taskList);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add Task"));

        inputPanel.add(new JLabel("Title:"));
        titleField = new JTextField();
        inputPanel.add(titleField);

        inputPanel.add(new JLabel("Category:"));
        categoryField = new JTextField();
        inputPanel.add(categoryField);

        inputPanel.add(new JLabel("Due Date (YYYY-MM-DD):"));
        dueDateField = new JTextField();
        inputPanel.add(dueDateField);

        JButton addButton = new JButton("Add Task");
        inputPanel.add(addButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        JButton viewTasksButton = new JButton("View Tasks");
        JButton completeTaskButton = new JButton("Mark as Completed");
        JButton deleteTaskButton = new JButton("Delete Task");

        buttonPanel.add(viewTasksButton);
        buttonPanel.add(completeTaskButton);
        buttonPanel.add(deleteTaskButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Event Listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        viewTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewTasks();
            }
        });

        completeTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markTaskAsCompleted();
            }
        });

        deleteTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTask();
            }
        });

        frame.setVisible(true);
    }

    private void addTask() {
        String title = titleField.getText();
        String category = categoryField.getText();
        String dueDate = dueDateField.getText();

        if (title.isEmpty() || category.isEmpty() || dueDate.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            TaskManager.addTask(title, category, dueDate);
            JOptionPane.showMessageDialog(frame, "Task added successfully");
            titleField.setText("");
            categoryField.setText("");
            dueDateField.setText("");
            viewTasks();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error adding task: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void markTaskAsCompleted() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a task to mark as completed", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Task task = TaskManager.getAllTasks().get(selectedIndex);
        TaskManager.markTaskAsCompleted(task.getId());
        viewTasks();
    }

    private void viewTasks() {
        listModel.clear();
        List<Task> tasks = TaskManager.getAllTasks();
        for (Task task : tasks) {
            listModel.addElement(task.toString());
        }
    }

    private void deleteTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a task to delete", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Task task = TaskManager.getAllTasks().get(selectedIndex);
        TaskManager.deleteTask(task.getId());
        viewTasks();
    }

    public static void main(String[] args) {
        new ToDoListAppUI();
    }
}
