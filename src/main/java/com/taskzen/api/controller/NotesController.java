package com.taskzen.api.controller;

import java.util.List;

import org.apache.coyote.BadRequestException;
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
import com.taskzen.api.entity.Note;
import com.taskzen.api.model.NoteModel;
import com.taskzen.api.service.NotesService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/notes")
@Slf4j
public class NotesController {

	@Autowired
	private NotesService noteService;

	@PostMapping("/create")
	public ResponseEntity<?> createNote(@RequestBody NoteModel model) {
		log.info("Request to create note: {}", model);
		try {
			Note note = noteService.createNote(model);
			log.info("Note created successfully with ID: {}", note.getId());
			return ResponseEntity.ok(note);
		} catch (Exception e) {
			log.error("Failed to create note", e);
			return ResponseEntity.status(500).body("Failed to create note: " + e.getMessage());
		}
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateNote(@RequestBody NoteModel model) {
		log.info("Request to update note: {}", model);
		try {
			Note updatedNote = noteService.updateNote(model);
			log.info("Request to update note: {}", model);
			return ResponseEntity.ok(updatedNote);
		} catch (BadRequestException e) {
			log.warn("Update failed: {}", e.getMessage());
			return ResponseEntity.badRequest().body("Update failed: " + e.getMessage());
		} catch (Exception e) {
			log.error("Unexpected error while updating note", e);
			return ResponseEntity.status(500).body("Unexpected error: " + e.getMessage());
		}
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<?> getNoteById(@PathVariable Integer id) {
		log.info("Request to get note by ID: {}", id);
		try {
			Note note = noteService.getNoteById(id);
			if (note != null) {
				log.info("Note found: {}", note);
				return ResponseEntity.ok(note);
			} else {
				log.warn("Note with ID {} not found", id);
				return ResponseEntity.status(404).body("Note with ID " + id + " not found.");
			}
		} catch (Exception e) {
			log.error("Error fetching note by ID {}", id, e);
			return ResponseEntity.status(500).body("Error fetching note: " + e.getMessage());
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteNoteById(@PathVariable Integer id) {
		log.info("Request to delete note by ID: {}", id);
		try {
			boolean deleted = noteService.deleteNoteById(id);
			if (deleted) {
				log.info("Note with ID {} deleted successfully", id);
				return ResponseEntity.ok("Note with ID: " + id + " has been deleted successfully.");
			} else {
				log.warn("Note with ID {} not found for deletion", id);
				return ResponseEntity.status(404).body("Note with ID: " + id + " not found.");
			}
		} catch (Exception e) {
			log.error("Error deleting note by ID {}", id, e);
			return ResponseEntity.status(500).body("Error deleting note: " + e.getMessage());
		}
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllNotes(@RequestParam(name = "username", required = true) String username) {
		log.info("Request to get all notes for username: {}", username);
		try {
			List<Note> notes = noteService.getAllNotes(username);
			if (notes.isEmpty()) {
				log.info("No notes found for username: {}", username);
				return ResponseEntity.noContent().build();
			}
			log.info("{} notes found for username: {}", notes.size(), username);
			return ResponseEntity.ok(notes);
		} catch (Exception e) {
			log.error("Error fetching notes for username {}", username, e);
			return ResponseEntity.status(500).body("Error fetching notes: " + e.getMessage());
		}
	}
}
