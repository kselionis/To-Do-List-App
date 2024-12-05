# To-Do List App

This project is a **To-Do List Application** implemented in Java. It provides a graphical user interface (GUI) built with Java Swing for managing tasks. Users can add, view, update, and delete tasks, as well as filter them by categories. The application uses a **MySQL database** to store the tasks persistently.

---

## Features

### 1. **Task Management**
- Add new tasks with the following attributes:
  - **Title**: A short description of the task.
  - **Category**: The category the task belongs to (e.g., Work, Personal).
  - **Due Date**: The date by which the task should be completed.
- View all tasks in a scrollable list.
- Delete tasks individually.
- Mark tasks as completed.

### 2. **Category Filtering**
- View tasks filtered by specific categories.
- Easily identify tasks belonging to a particular category.

### 3. **Database Integration**
- Stores all tasks in a **MySQL database**.
- Persistent storage ensures that tasks remain available across sessions.

### 4. **Graphical User Interface (GUI)**
- Simple and intuitive user interface designed with **Java Swing**.
- Organized panels for input fields, task list, and action buttons.

---

## Technologies Used

### Backend
- **Java 17**
- **MySQL Connector/J** (JDBC Driver for MySQL)

### Database
- **MySQL** (v8.0 or higher)

### Frontend
- **Java Swing**

---

## Prerequisites

1. Install **MySQL** and create a database:
   ```sql
   CREATE DATABASE todo_list_app;
   USE todo_list_app;

   CREATE TABLE tasks (
       id INT AUTO_INCREMENT PRIMARY KEY,
       title VARCHAR(255) NOT NULL,
       category VARCHAR(100),
       due_date DATE,
       completed BOOLEAN DEFAULT FALSE
   );
   ```

2. Install **Java 17** or later.
3. Download the MySQL Connector/J `.jar` file and place it in the `lib/` directory of the project.

---

## How to Run the Application

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/todo-list-app.git
   cd todo-list-app
   ```

2. Compile the project:
   ```bash
   javac -cp .:lib/mysql-connector-j-9.1.0.jar src/**/*.java
   ```

3. Run the application:
   ```bash
   java -cp .:lib/mysql-connector-j-9.1.0.jar src.ToDoListAppUI
   ```

---

## Code Structure

### 1. `DatabaseConnection.java`
Handles the connection to the MySQL database.

### 2. `Task.java`
A model class that represents a task with attributes like `id`, `title`, `category`, `dueDate`, and `completed`.

### 3. `TaskManager.java`
Provides the logic for interacting with the database, including:
- Adding tasks
- Fetching tasks
- Deleting tasks
- Updating task statuses

### 4. `ToDoListAppUI.java`
The main class that initializes and manages the graphical user interface.

---

## Example Screenshots

- **Add Task Panel**: Enter task details like title, category, and due date.
- **Task List**: Displays tasks in a scrollable list with category and status.
- **Action Buttons**: Includes buttons for viewing, adding, deleting, and marking tasks as completed.

---

## Future Improvements

1. Add user authentication to personalize task lists.
2. Enable task export to CSV for offline use.
3. Improve UI design with more modern libraries like JavaFX.
4. Add support for recurring tasks.

---

## License

This project is licensed under the MIT License. Feel free to use and modify it.

---

## Contributions

Contributions are welcome! Fork the repository, make your changes, and submit a pull request.

---
