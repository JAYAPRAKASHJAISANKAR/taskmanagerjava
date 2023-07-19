const taskForm = document.getElementById("taskForm");
const taskInput = document.getElementById("taskInput");
const taskList = document.getElementById("taskList");
let tasks = [];

// Load tasks from local storage on page load
document.addEventListener("DOMContentLoaded", function () {
  tasks = getTasksFromLocalStorage();
  tasks.forEach((task) => {
    renderTask(task);
  });
});

taskForm.addEventListener("submit", function (e) {
  e.preventDefault();

  const task = taskInput.value;

  // Create a new task object
  const newTask = {
    id: Date.now(),
    description: task,
  };

  tasks.push(newTask);

  // Save tasks to local storage
  saveTasksToLocalStorage(tasks);

  // Render the task on the page
  renderTask(newTask);

  // Clear the input field
  taskInput.value = "";
});

function renderTask(task) {
  const taskItem = document.createElement("li");
  taskItem.innerHTML = `
    <span>${task.description}</span>
    <button class="edit-btn" onclick="editTask(${task.id})">Edit</button>
    <button class="delete-btn" onclick="deleteTask(${task.id})">Delete</button>
  `;
  taskList.appendChild(taskItem);
}

function editTask(taskId) {
  const newDescription = prompt("Edit the task:");
  if (newDescription) {
    const taskIndex = tasks.findIndex((task) => task.id === taskId);
    if (taskIndex !== -1) {
      tasks[taskIndex].description = newDescription;
      saveTasksToLocalStorage(tasks);
      taskList.innerHTML = "";
      tasks.forEach((task) => {
        renderTask(task);
      });
    }
  }
}

function deleteTask(taskId) {
  if (confirm("Delete this task?")) {
    tasks = tasks.filter((task) => task.id !== taskId);
    saveTasksToLocalStorage(tasks);
    taskList.innerHTML = "";
    tasks.forEach((task) => {
      renderTask(task);
    });
  }
}

function saveTasksToLocalStorage(tasks) {
  localStorage.setItem("tasks", JSON.stringify(tasks));
}

function getTasksFromLocalStorage() {
  const tasks = localStorage.getItem("tasks");
  return tasks ? JSON.parse(tasks) : [];
}
