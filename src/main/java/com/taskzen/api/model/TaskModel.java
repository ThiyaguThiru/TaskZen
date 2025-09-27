package com.taskzen.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskModel {
	private Integer id;
	private String username;
	private String title;
	private String content;
}
