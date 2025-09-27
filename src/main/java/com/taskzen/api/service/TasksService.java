package com.taskzen.api.service;

import java.util.List;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskzen.api.entity.Task;
import com.taskzen.api.model.TaskModel;
import com.taskzen.api.repository.TasksRepository;

@Service
public class TasksService {

	@Autowired
	private TasksRepository taskRepository;

	public Task createTask(TaskModel model) {
		Task task = new Task();
		BeanUtils.copyProperties(model, task);
		task.setId(null);
		if (task.getIsCompleted() == null) {
			task.setIsCompleted(false);
		}
		taskRepository.save(task);
		return task;
	}

	public Task updateTask(TaskModel model) throws BadRequestException {
		if (model.getId() == null) {
			throw new BadRequestException("Id is required to update the Task.");
		}
		Optional<Task> optionalTask = taskRepository.findById(model.getId());
		if (!optionalTask.isPresent()) {
			throw new BadRequestException("Task not found with ID: " + model.getId());
		}
		Task existingTask = optionalTask.get();
		BeanUtils.copyProperties(model, existingTask);
		taskRepository.save(existingTask);
		return existingTask;
	}

	public Task getTaskById(Integer id) {
		return taskRepository.findById(id).orElse(null);
	}

	public boolean deleteTaskById(Integer id) {
		if (taskRepository.existsById(id)) {
			taskRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public List<Task> getAllTasks(String username) {
		return taskRepository.findAllByUsername(username);
	}
}
