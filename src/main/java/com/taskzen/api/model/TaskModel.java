package com.taskzen.api.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskModel {
	private Integer id;
	@Schema(description = "User email address", example = "user@example.com", type = "string", format = "email")
	@Email(message = "Username must be a valid email address")
	@NotBlank(message = "Username is required")
	private String username;
	private String title;
	private String content;
	@Schema(description = "Due date and time in yyyy-MM-dd HH:mm:ss format", example = "2025-09-27 18:30:00", type = "string", pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dueDate;
	@Schema(description = "Reminder date and time in yyyy-MM-dd HH:mm:ss format", example = "2025-09-27 17:30:00", type = "string", pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime reminderDate;
}
