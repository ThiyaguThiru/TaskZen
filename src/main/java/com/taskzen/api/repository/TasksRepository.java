package com.taskzen.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskzen.api.entity.Task;

public interface TasksRepository extends JpaRepository<Task, Integer> {

	List<Task> findAllByUsername(String username);

}
