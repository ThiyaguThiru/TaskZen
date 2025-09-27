package com.taskzen.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.taskzen.api.entity.Task;
import com.taskzen.api.model.TaskModel;
import com.taskzen.api.service.TasksService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/tasks")
@Slf4j
public class TasksController {

	@Autowired
	private TasksService taskService;

	@PostMapping("/create")
	public ResponseEntity<?> createTask(@RequestBody TaskModel model) {
		log.info("Request to create task: {}", model);
		try {
			Task task = taskService.createTask(model);
			log.info("Task created successfully with ID: {}", task.getId());
			return ResponseEntity.ok(task);
		} catch (Exception e) {
			log.error("Failed to create Task", e);
			return ResponseEntity.status(500).body("Failed to create task: " + e.getMessage());
		}
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateTask(@RequestBody TaskModel model) {
		log.info("Request to update task: {}", model);
		try {
			Task updatedTask = taskService.updateTask(model);
			log.info("Task updated successfully with ID: {}", updatedTask.getId());
			return ResponseEntity.ok(updatedTask);
		} catch (Exception e) {
			log.error("Failed to update task", e);
			return ResponseEntity.status(500).body("Failed to update task: " + e.getMessage());
		}
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<?> getTaskById(@PathVariable Integer id) {
		log.info("Request to get task by ID: {}", id);
		try {
			Task task = taskService.getTaskById(id);
			if (task != null) {
				log.info("Task found: {}", task);
				return ResponseEntity.ok(task);
			} else {
				log.warn("Task with ID {} not found", id);
				return ResponseEntity.status(404).body("Task with ID " + id + " not found.");
			}
		} catch (Exception e) {
			log.error("Error fetching task by ID {}", id, e);
			return ResponseEntity.status(500).body("Error fetching task: " + e.getMessage());
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteTaskById(@PathVariable Integer id) {
		log.info("Request to delete task by ID: {}", id);
		try {
			boolean deleted = taskService.deleteTaskById(id);
			if (deleted) {
				log.info("Task with ID {} deleted successfully", id);
				return ResponseEntity.ok("Task with ID " + id + " has been deleted successfully.");
			} else {
				log.warn("Task with ID {} not found for deletion", id);
				return ResponseEntity.status(404).body("Task with ID " + id + " not found.");
			}
		} catch (Exception e) {
			log.error("Error deleting task by ID {}", id, e);
			return ResponseEntity.status(500).body("Error deleting task: " + e.getMessage());
		}
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllTasks(@RequestParam(name = "username", required = true) String username) {
		log.info("Request to get all tasks for username: {}", username);
		try {
			List<Task> tasks = taskService.getAllTasks(username);
			if (tasks.isEmpty()) {
				log.info("No tasks found for username: {}", username);
				return ResponseEntity.noContent().build();
			}
			log.info("{} tasks found for username: {}", tasks.size(), username);
			return ResponseEntity.ok(tasks);
		} catch (Exception e) {
			log.error("Error fetching tasks for username {}", username, e);
			return ResponseEntity.status(500).body("Error fetching tasks: " + e.getMessage());
		}
	}
}
