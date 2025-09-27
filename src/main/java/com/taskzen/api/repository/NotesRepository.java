package com.taskzen.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskzen.api.entity.Note;

public interface NotesRepository extends JpaRepository<Note, Integer> {

}
