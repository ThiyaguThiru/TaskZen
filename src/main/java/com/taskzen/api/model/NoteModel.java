package com.taskzen.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteModel {
	private Integer id;
	@Schema(description = "User email address", example = "user@example.com", type = "string", format = "email")
	@Email(message = "Username must be a valid email address")
	@NotBlank(message = "Username is required")
	private String username;
	private String title;
	private String content;
}
