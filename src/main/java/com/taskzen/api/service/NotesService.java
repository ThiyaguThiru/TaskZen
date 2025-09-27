package com.taskzen.api.service;

import java.util.List;
import java.util.Optional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.taskzen.api.entity.Note;
import com.taskzen.api.model.NoteModel;
import com.taskzen.api.repository.NotesRepository;

@Service
public class NotesService {
	
	@Autowired
	private NotesRepository noteRepository;

	public Note createNote(NoteModel model) {
		Note note = new Note();
		BeanUtils.copyProperties(model, note);
		noteRepository.save(note);
		return note;
	}

	public Note updateNote(NoteModel model) throws BadRequestException {
		if (model.getId() == null) {
			throw new BadRequestException("Id is Required to Update the Note.");
		}
		Optional<Note> optionalNote = noteRepository.findById(model.getId());
		if (!optionalNote.isPresent()) {
			throw new BadRequestException("Note not found with ID: " + model.getId());
		}
		Note existingNote = optionalNote.get();
		BeanUtils.copyProperties(model, existingNote);
		noteRepository.save(existingNote);
		return existingNote;
	}

	public Note getNoteById(Integer id) {
		Note note = noteRepository.findById(id).orElse(null);
		return note;
	}

	public boolean deleteNoteById(Integer id) {
		if (noteRepository.existsById(id)) {
	        noteRepository.deleteById(id);
	        return true;
	    }
	    return false;
	}

	public List<Note> getAllNotes(String username) {
		List<Note> notes = noteRepository.findAllByUsername(username);
		return notes;
	}
	
}
