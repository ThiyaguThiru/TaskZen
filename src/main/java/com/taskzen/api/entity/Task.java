package com.taskzen.api.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank
	@Email
	private String username;
	@NotBlank
	@Column(columnDefinition = "TEXT")
	private String title;
	@NotBlank
	@Column(columnDefinition = "TEXT")
	private String content;
	@NotBlank
	private LocalDateTime dueDate;
	
	private LocalDateTime reminderDate;
	
	@Column(columnDefinition = "BOOLEAN DEFAULT false")
	private Boolean isCompleted;
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@UpdateTimestamp
	private LocalDateTime updatedDate;	
}
